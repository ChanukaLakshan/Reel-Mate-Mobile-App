# ReelMate - Quick Start Guide

## ✅ BUILD STATUS: SUCCESS

Your app is ready to run!

## 🚀 Run the App (Choose One Method)

### Method 1: Android Studio (Recommended)
1. Open Android Studio
2. File → Open → Select `newReelMate` folder
3. Wait for Gradle sync
4. Click Run button (▶) or press Shift+F10
5. Select device/emulator
6. App will launch automatically

### Method 2: Command Line
```powershell
cd "C:\Users\Chanuka Lakshan\AndroidStudioProjects\newReelMate"
.\gradlew installDebug
```

### Method 3: Direct APK Install
```powershell
adb install "C:\Users\Chanuka Lakshan\AndroidStudioProjects\newReelMate\app\build\outputs\apk\debug\app-debug.apk"
```

## 📱 Using the App

### Login Screen
- Enter any email and password (mock authentication)
- Click "Login" to enter the app

### Home Screen
- Browse movies
- Click on a movie card to view details
- Click "List" button to add to watchlist
- Click "Mark" button to mark as watched
- Use top icons to navigate:
  - 📋 Lists icon → My Lists
  - 🔔 Bell icon → Notifications (placeholder)
  - 👤 Profile icon → Profile

### Movie Details
- View full movie information
- Scroll to see reviews
- Click "Write Review" to add your review
- Click "Add to Watchlist" to save movie

### My Lists
- View your custom movie lists
- Click on a list to see movies
- Use share/delete buttons on each list

### Profile
- View your statistics
- Check movies watched, lists created, reviews written
- Access Edit Profile, Settings, and Logout

## 🎨 App Theme

**Dark Mode** with Netflix-inspired colors:
- Primary Red: #E50914
- Golden Yellow: #F5C518
- Dark backgrounds for comfortable viewing

## 📂 Project Location

```
C:\Users\Chanuka Lakshan\AndroidStudioProjects\newReelMate\
```

## 🔧 Useful Commands

```powershell
# Rebuild app
.\gradlew clean assembleDebug

# Check for issues
.\gradlew check

# List connected devices
adb devices

# View app logs
adb logcat | Select-String "ReelMate"
```

## 🐛 Troubleshooting

### App won't install?
- Enable "Install from Unknown Sources" on device
- Check device is connected: `adb devices`
- Try uninstalling old version first

### Gradle sync fails?
- File → Invalidate Caches / Restart in Android Studio
- Delete `.gradle` folder and sync again

### Emulator not starting?
- Check Android SDK is installed
- Try creating a new emulator (Tools → AVD Manager)
- Ensure virtualization is enabled in BIOS

## 📞 Support

Check these files for more info:
- `README.md` - Full documentation
- `app/build.gradle.kts` - Build configuration
- `AndroidManifest.xml` - App configuration

---

**Enjoy your ReelMate Movie App! 🎬**

