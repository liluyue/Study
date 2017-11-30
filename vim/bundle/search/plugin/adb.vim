cnoremap <F2> :call AdbActivity()<cr>
:if exists("g:debugAdb")
  finish 
:endif
:let g:debugAdb=1
echo g:debugAdb
let g:apkPackageList=["com.excelliance.dualaid","com.excean.mvoice","com.excean.wxaid",'6']
"应用安装
:command! -nargs=+ -complete=file AdbInstall :let result=split(system('adb install '.fnamemodify(<q-args>,':p')),'\n') | let result=result[1:2]+result[-4:-1] |for item in result | echo item |endfor
"应用卸载
:command! -nargs=1 AdbUninstall :call AdbUninstall(<f-args>)
"应用清空数据
:command! -nargs=1 AdbClear :call AdbClear(<f-args>)
"logcat
:command! -nargs=1 -complete=file AdbLogCatV :execute '!start /b adb logcat -v time > ' .<f-args>
:command!  AdbLogCatC :execute '!start /b adb logcat -c' 
"adb pull
:command! -nargs=+ -complete=custom,SdcardFile AdbPull :execute '!start /b adb pull ' <q-args>
"adb push
:command! -nargs=1 -complete=file AdbPush :execute '!start /b adb push ' <q-args>."/sdcard/"

:function! SdcardFile(A,L,P)
:"return substitute(system('adb shell ls /sdcard/'),'\r','','g')
:let l:files=systemlist('adb shell ls /sdcard/')
:  let l:files=map(l:files,'"/sdcard/".v:val')
:  return substitute(join(l:files,"\n"),"\r","","g")
:endfunction

function! AdbActivity()
	let tmpfile = tempname()
	:exe "tabedit ". tmpfile
  :0,$d
  :r !adb shell dumpsys activity activities
  :w
  :  let buflist = []
  :for i in range(tabpagenr('$'))
   :   call extend(buflist, tabpagebuflist(i + 1))
   :endfor
   :echo buflist
 endfunction


"uninstall by package
function! AdbUninstall(package)
 if (a:package>0)
   let a:pkg=g:apkPackageList[a:package-1]
 else
   let a:pkg=a:package
 endif
 echo a:pkg
:echo  system('adb uninstall '.a:pkg)

endfunction


"clear apk data by package
function! AdbClear(package)
 if (a:package>0)
   let a:pkg=g:apkPackageList[a:package-1]
 else
   let a:pkg=a:package
 endif
 echo a:pkg
:echo  system('adb shell pm clear '.a:pkg)
endfunction
