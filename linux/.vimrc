set number 
syntax on
set ruler
set incsearch
set hlsearch
set cursorline 
set tabstop=4
set enc=utf-8
set fencs=utf-8,ucs-bom,shift-jis,gb18030,gbk,gb2312,cp936
set showmatch
set matchtime=1
colorscheme darkblue
"Set mapleader
let mapleader = ","

"Fast reloading of the .vimrc
map <silent> <leader>ss :source ~/.vimrc<cr>
"Fast editing of .vimrc
map <silent> <leader>ee :e ~/.vimrc<cr>
"When .vimrc is edited, reload it
autocmd! bufwritepost .vimrc source ~/.vimrc 
"Favorite filetype
set ffs=unix,dos,mac
set showcmd
map <leader>fd :se ff=dos<cr>
map <leader>fd :se ff=unix<cr>

":com! -nargs=* G !grep -snr --exclude=tags <args>
:com! -nargs=* G /<args><CR>vimg <args> <CR>
nmap <F2> :vimg 'fatal\\|crash\c' %<CR>:copen<CR> 
"Restore cursor to file position in previous editing session
set viminfo='10,"100,:20,%,n~/.viminfo
"source ~/vimgrep.vim
map <C-j> <C-W>j
map <C-k> <C-W>k
map <C-h> <C-W>h
map <C-l> <C-W>l
imap <m-$> <esc>$a
imap <m-0> <esc>0i
:set sessionoptions+=unix,slash
	set nocp                    " 'compatible' is not set
	filetype plugin on          " plugins are enabled
set shiftwidth=4
set softtabstop=4
