# ReelMate App Launcher Script
# This script will build and install the ReelMate app

Write-Host "============================================" -ForegroundColor Cyan
Write-Host "ReelMate Movie App Launcher" -ForegroundColor Yellow
Write-Host "============================================" -ForegroundColor Cyan
Write-Host ""

# Set location to project directory
Set-Location "C:\Users\Chanuka Lakshan\AndroidStudioProjects\newReelMate"

# Check for ADB and devices
Write-Host "Checking for connected Android devices..." -ForegroundColor Green
$adbPath = "C:\Users\Chanuka Lakshan\AppData\Local\Android\Sdk\platform-tools\adb.exe"

if (Test-Path $adbPath) {
    & $adbPath devices
    Write-Host ""

    Write-Host "Building and installing app..." -ForegroundColor Green
    & .\gradlew.bat installDebug

    Write-Host ""
    Write-Host "============================================" -ForegroundColor Cyan
    Write-Host "Installation Complete!" -ForegroundColor Green
    Write-Host ""
    Write-Host "The ReelMate app should now be installed." -ForegroundColor Yellow
    Write-Host "Look for 'ReelMate' in your app drawer!" -ForegroundColor Yellow
    Write-Host "============================================" -ForegroundColor Cyan
} else {
    Write-Host "ADB not found. Please ensure Android SDK is installed." -ForegroundColor Red
    Write-Host ""
    Write-Host "Alternative: Open Android Studio and click the Run button." -ForegroundColor Yellow
}

Write-Host ""
Write-Host "Press any key to exit..."
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")

