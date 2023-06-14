:: Directorio de java SDK
SET JAVA_HOME="C:\Program Files\Java\jdk1.8.0_261"

:: direccion de las fuentes SRC de proyecto netbeans IDL "CorbaFiboInterface"
SET PATH_IDL="D:\Javier\Universidad\NetBeans Projects\AnilloCorba\src"

:: nombre del archivo IDL
SET IDL_NAME=peer.idl

:: comando para compilar
%JAVA_HOME%\bin\idlj -fall -td %PATH_IDL% %PATH_IDL%\%IDL_NAME%