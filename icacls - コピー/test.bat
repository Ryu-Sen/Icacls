@echo off
color 0a

echo WARNING: This might take minutes or hours depending on drive volume
echo Please make sure your drive S is not in use

:PROMPT
SET /P AREYOUSURE=Continue (Y/[N])?
IF /I "%AREYOUSURE%" NEQ "Y" GOTO END

cls

echo Mapping File Server to Local Drive S
net use S: \\192.168.118.70\share v7EZDhnu /user:LISTJP\Administrator

echo Writing permissions information on permissions.txt
@echo off
for /d %%i in (S:\\*) do (
icacls "%%i" /q >> permissions.txt 2> errors.txt
cd "%%i"
icacls "%%i\*." /q >> permissions.txt 2> errors.txt
)

echo Deleting errors.txt
timeout 5
del errors.txt

echo Removing Mapping
net use S: /delete

echo Writing permissions.txt on permissions.xlsx (Java required)
echo This might take a while DO NOT CLOSE
timeout 10

java -jar permissions.jar

:END
endlocal