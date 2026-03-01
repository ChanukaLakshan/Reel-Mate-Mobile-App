# 🚀 HOW TO RUN REELMATE APP - Step by Step

## ✅ Your app is ready! The project folder is now open.

---

## METHOD 1: Run from Android Studio (RECOMMENDED - Easiest)

### Step 1: Open Android Studio
- Double-click on **Android Studio** icon on your desktop
- OR search for "Android Studio" in Windows Start menu

### Step 2: Open the Project
- In Android Studio, click **"Open"**
- Navigate to: `C:\Users\Chanuka Lakshan\AndroidStudioProjects\newReelMate`
- Click **OK**

### Step 3: Wait for Gradle Sync
- Android Studio will automatically sync Gradle (takes 30-60 seconds)
- Wait for "Gradle Build Finished" message at the bottom

### Step 4: Start an Emulator (if you don't have a physical device)
- Click **"Device Manager"** icon (phone icon) on the right toolbar
- Click **"Create Device"**
- Select **"Pixel 5"** or any phone model → Click **Next**
- Select **"S"** (API 31) or latest Android version → Click **Next**
- Click **Finish**
- Click the **Play ▶** button next to your device to start it

### Step 5: Run the App
- Click the green **RUN ▶** button at the top toolbar
- OR press **Shift + F10**
- Select your device/emulator
- Wait for installation (takes 10-20 seconds)
- **ReelMate app will launch automatically!** 🎉

---

## METHOD 2: Run Using Scripts (QUICK)

### Option A: Double-click `RUN_APP.bat`
1. Open the project folder (already open for you!)
2. Double-click **`RUN_APP.bat`**
3. Wait for installation
4. Open ReelMate from your device's app drawer

### Option B: Run PowerShell Script
1. Right-click **`RUN_APP.ps1`**
2. Select **"Run with PowerShell"**
3. Wait for installation
4. Launch ReelMate on your device

---

## METHOD 3: Manual Command Line

### If you have a device connected via USB:

1. **Enable USB Debugging on your Android device:**
   - Settings → About Phone → Tap "Build Number" 7 times
   - Go back → Developer Options → Enable "USB Debugging"
   - Connect device via USB cable

2. **Run these commands:**
```powershell
cd "C:\Users\Chanuka Lakshan\AndroidStudioProjects\newReelMate"
.\gradlew installDebug
```

3. **Open ReelMate app on your device**

---

## METHOD 4: Install APK Directly

### The APK file is already built at:
```
C:\Users\Chanuka Lakshan\AndroidStudioProjects\newReelMate\app\build\outputs\apk\debug\app-debug.apk
```

### On Physical Device:
1. Copy `app-debug.apk` to your phone
2. Open the file on your phone
3. Allow "Install from Unknown Sources" if prompted
4. Tap **Install**
5. Open ReelMate

### On Emulator:
1. Start an emulator from Android Studio
2. Drag and drop `app-debug.apk` onto the emulator window
3. Open ReelMate from the app drawer

---

## 🎬 Using the App

### First Screen - Login
- **Email:** Enter any email (e.g., `test@example.com`)
- **Password:** Enter any password (e.g., `123456`)
- Click **Login**

### Home Screen
- Browse movies
- Click on any movie to see details
- Use the "List" button to add to watchlist
- Use the "Mark" button to mark as watched

### Navigation
- **📋 Lists Icon** → View your custom movie lists
- **🔔 Bell Icon** → Notifications (placeholder)
- **👤 Profile Icon** → View your profile and stats

### Features to Test
✅ Browse movies with posters and ratings  
✅ Search for movies (UI ready)  
✅ Add movies to watchlist  
✅ Mark movies as watched  
✅ View detailed movie information  
✅ Read and write reviews  
✅ Create and manage custom lists  
✅ View profile statistics  

---

## 🐛 Troubleshooting

### "Gradle sync failed"
- In Android Studio: File → Invalidate Caches / Restart
- Click **Invalidate and Restart**

### "No devices found"
- For physical device: Enable USB debugging and connect via USB
- For emulator: Device Manager → Create new device

### "Installation failed"
- Uninstall old version first
- Check device has enough storage
- Try: `.\gradlew clean installDebug`

### "App won't open"
- Check if app is installed: Look for "ReelMate" icon
- Try uninstalling and reinstalling
- Check device Android version (needs Android 7.0+)

---

## 📞 Quick Reference

**Project Location:**
```
C:\Users\Chanuka Lakshan\AndroidStudioProjects\newReelMate
```

**APK Location:**
```
app\build\outputs\apk\debug\app-debug.apk
```

**Rebuild App:**
```powershell
.\gradlew clean assembleDebug
```

**Check Connected Devices:**
```powershell
C:\Users\Chanuka Lakshan\AppData\Local\Android\Sdk\platform-tools\adb.exe devices
```

---

## ⚡ FASTEST WAY (RIGHT NOW):

1. ✅ **Android Studio is installed** (found at: C:\Program Files\Android\Android Studio)
2. ✅ **Project folder is OPEN** (check your File Explorer)
3. 📱 **Just open Android Studio** and click the **RUN ▶ button**!

---

**That's it! Your ReelMate app is ready to run! 🎉**

*Any issues? Check the troubleshooting section above.*

