set nocompatible              " 去除VI一致性,必须
filetype off                  " 必须
set rtp+=$VIMRUNTIME/bundle/Vundle.vim
" 设置包括vundle和初始化相关的runtime path
set rtp+=~/.vim/bundle/Vundle.vim
call vundle#begin()
" 另一种选择, 指定一个vundle安装插件的路径
"call vundle#begin('~/some/path/here')

" 让vundle管理插件版本,必须
Plugin 'VundleVim/Vundle.vim'
Plugin 'davidhalter/jedi-vim'
Plugin 'Yggdroot/LeaderF'
"let g:Lf_ShortcutF='<c-s-f>'
Plugin 'iamcco/markdown-preview.vim'
Bundle 'ervandew/supertab'
"Plugin 'https://github.com/python-mode/python-mode.git'
" 你的所有插件需要在下面这行之前
call vundle#end()            " 必须
filetype plugin indent on    " 必须 加载vim自带和插件相应的语法和文件类型相关脚本
" 忽视插件改变缩进,可以使用以下替代:
"filetype plugin on
   
set number 
syntax on
set ruler
set incsearch
set hlsearch
set cursorline 
set tabstop=4
"set enc=utf-8
set fencs=utf-8,ucs-bom,shift-jis,gb18030,gbk,gb2312,cp936
set nobackup
"set noswapfile
 set noundofile
set showmatch
set matchtime=1
set nowrapscan
colorscheme darkblue
"Set mapleader
let mapleader = ","
"map <c-f> <PageDown>
unmap <C-F>
unmap <C-A>
"Fast reloading of the .vimrc
map <silent> <leader>ss :source ~/.vimrc<cr>
"Fast editing of .vimrc
map <silent> <leader>ee :e ~/.vimrc<cr>
"When .vimrc is edited, reload it
autocmd! bufwritepost .vimrc source ~/.vimrc 
"Favorite filetype
set guifont=courier_new:h20

au GUIEnter * simalt ~x
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
highlight CursorLine   cterm=NONE ctermbg=darkblue ctermfg=green guibg=black guifg=green
