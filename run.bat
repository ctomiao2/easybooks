set base_dir=%~dp0
echo %base_dir%
java -cp "%base_dir%\\bin;%base_dir%\\lib\\fastjson.jar" Main
@pause