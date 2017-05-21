let search_crash=1
nmap <F2>    :call Search_crash('')<cr>
nmap <F3>    :call Search_crash(expand('<cword>'))<cr>
nmap <F4>   :call PositionDrawable(expand('<cWORD>'),expand('%:p:h'))<cr>
nmap <F5>    :call Search_crash(expand('<cword>'))<cr>
cnoremap <F2> :call Adb_Activity()<cr>
noremap <a-left> :tabp<cr>
noremap <a-right> :tabn<cr>
au BufRead,BufNewFile * set filetype=logcat 
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

function! Adb_Activity()
  :tabedit adb_activity.txt
  :0,$d
  :r !adb shell dumpsys activity activities
  :w
  :  let buflist = []
  :for i in range(tabpagenr('$'))
   :   call extend(buflist, tabpagebuflist(i + 1))
   :endfor
   :echo buflist
 endfunction

"show window of cmd result
function! Show_CmdResult()
  let a:winnr=bufwinnr(s:title)
  if (a:winnr>=0)
    execute a:winnr . 'wincmd w'
    execute 'normal ggdG'
  else
    let a:lineCounter=10
    execute 'belowright '. a:lineCounter .'split ++bad=drop '.s:title
    setlocal buftype=nofile bufhidden=wipe nobuflisted noswapfile nowrap
  endif
endfunction

"uninstall by package
function! Adb_Uninstall(package)
 if (a:package==1)
  let  a:pkg="com.excelliance.dualaid"
 else
   let a:pkg=a:package
 endif
  call  Show_CmdResult()
 "!start /b adb uninstall com.excelliance.dualaid

 echo a:pkg
:execute '$r !adb uninstall '.a:pkg

endfunction

let s:title='cmd result'

"clear apk data by package
function! Adb_Clear(package)
 if (a:package==1)
  let  a:pkg="com.excelliance.dualaid"
 else
   let a:pkg=a:package
 endif
 call Show_CmdResult()
"!start /min adb shell pm clear com.excelliance.dualaid
:execute '$r !adb shell pm clear '.a:pkg
endfunction


let s:translator_engines = {
    \ 'youdao': 'http://fanyi.youdao.com/openapi.do?keyfrom=FuDesign2008&key=1676087853&type=data&doctype=json&version=1.1&q=<QUERY>',
    \ 'baidu': 'http://openapi.baidu.com/public/2.0/bmt/translate?client_id=K4GwmBaiSfbCd0a6OfOCpHcd&q=<QUERY>&from=auto&to=auto'
    \}
let g:translator_engine="youdao"
function! Tran(word)
  "call Show_CmdResult()
   let a:url=substitute(s:translator_engines[g:translator_engine],"<QUERY>",a:word,"")
  "execute 'Utl openLink '. a:url
  execute '0r !start '.a:url
endfunction
