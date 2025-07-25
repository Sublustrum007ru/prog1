git add ./ 
timeout /t 15 /nobreak 
set /p commit="Please enter the commit: " 
git commit -m "%commit% %Computername% %date:~0,2%/%date:~3,2%/%date:~-4% %time:~0,2%:%time:~3,2%:%time:~6,2%" 
timeout /t 15 /nobreak 
git push https://github.com/Sublustrum007ru/prog1.git
git remote add origin https://github.com/Sublustrum007ru/prog1.git