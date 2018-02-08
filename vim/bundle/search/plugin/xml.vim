function Xml()

    set filetype=xml

    :%s/></>\r</g "把><替换成>回车<

    :normal gg=G<cr>

endfunction

:command! Xml call Xml() 
