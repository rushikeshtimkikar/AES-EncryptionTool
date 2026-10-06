@echo off

javac --module-path "C:\javafx-sdk-27\lib" --add-modules javafx.controls AESEncryption.java EncryptionUI.java

if %errorlevel% neq 0 (
    echo.
    echo Compilation failed!
    pause
    exit /b
)

java --module-path "C:\javafx-sdk-27\lib" --add-modules javafx.controls EncryptionUI