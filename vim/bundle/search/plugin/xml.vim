function Xml()

    set filetype=xml

    :%s/></>\r</g "��><�滻��>�س�<

    :normal gg=G<cr>

endfunction

:command! Xml call Xml() 
