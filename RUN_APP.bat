@echo off
echo ============================================
echo ReelMate App Launcher
echo ============================================
echo.

cd /d "%~dp0"

echo Checking for connected devices...
"%LOCALAPPDATA%\Android\Sdk\platform-tools\adb.exe" devices
echo.

echo Building and installing ReelMate...
call gradlew.bat installDebug

echo.
echo ============================================
echo Installation Complete!
echo.
echo The app should now be installed on your device.
echo Look for "ReelMate" in your app drawer.
echo ============================================
pause

