let search_crash=1
nmap <F2>    :call Search_crash('')<cr>
nmap <F3>   :call PositionDrawable(expand('<cWORD>'),expand('%:p:h'))<cr>
function! Search_crash(a)
: if a:a == "" 
: let s:search='crash\|fatal\|excep\c'
: else 
: let s:search=a:a
: endif
:cclose
":echo s:search
:execute ":vimg ". s:search ." %"
:cw
:highlight MyGroup cterm=reverse gui=reverse ctermfg=6
:call matchadd("MyGroup",s:search)
endfunction

function! Uniq()
:sort 
:g/^\(.*\)$\n\1$/d
endfunction

function! PositionDrawable(drawable,path)
 :let  s:cword='"'.a:drawable.'"\|/\s*'.a:drawable.'\s*"\|/\s*'.a:drawable."\\s*<"
 :echo s:cword
 :let s:path=a:path."/src/** " .a:path."/res/**  ". a:path."/platform/src/**"
 :exec "vimgrep '".s:cword ."'  ".s:path 
endfunction
