# ImageToolkit - Android Image Processing App

A comprehensive Android image utility app built with Jetpack Compose and Material 3. Users can pick images from camera or gallery and apply various image processing tools.

## 🚀 Features

### ✅ Implemented
- **Crop & Resize**: Full-featured image cropping with uCrop library
  - Multiple aspect ratios (1:1, 4:3, 16:9, Free)
  - Custom width/height resizing
  - Rotate and flip functionality
  - Save to gallery and share options

### 🔄 Coming Soon (Placeholder Screens)
- Background Removal
- Filters & Effects
- Format Conversion
- Rotate & Flip
- Image Compression

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material 3
- **Architecture**: MVVM with Hilt Dependency Injection
- **Navigation**: Jetpack Navigation Compose
- **Image Processing**: uCrop library
- **Image Loading**: Coil
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## 📱 App Structure

```
app/
├── src/main/java/com/imagetoolkit/app/
│   ├── data/
│   │   ├── ImageFeature.kt              # Feature data models
│   │   └── repository/
│   │       └── ImageRepository.kt       # Image handling repository
│   ├── ui/
│   │   ├── components/
│   │   │   ├── FeatureCard.kt           # Reusable feature card component
│   │   │   └── ImagePickerDialog.kt     # Image selection dialog
│   │   ├── navigation/
│   │   │   └── ImageToolkitNavigation.kt # Navigation setup
│   │   ├── screens/
│   │   │   ├── home/
│   │   │   │   └── HomeScreen.kt        # Main screen with feature grid
│   │   │   ├── crop/
│   │   │   │   └── CropResizeScreen.kt  # Crop & resize functionality
│   │   │   └── placeholder/
│   │   │       └── PlaceholderScreen.kt # Coming soon screens
│   │   ├── theme/
│   │   │   ├── Color.kt                 # Material 3 color definitions
│   │   │   ├── Theme.kt                 # Theme configuration
│   │   │   └── Type.kt                  # Typography definitions
│   │   └── viewmodel/
│   │       └── ImageViewModel.kt        # Main view model
│   ├── utils/
│   │   ├── CropUtils.kt                 # uCrop integration utilities
│   │   └── ImagePickerUtils.kt          # Image picking utilities
│   ├── MainActivity.kt                  # Main activity
│   └── ImageToolkitApplication.kt       # Application class
└── src/main/res/
    ├── drawable/                        # Vector drawable icons
    ├── values/                          # Strings, colors, themes
    └── xml/                             # File provider configuration
```

## 🎨 UI/UX Features

- **Material 3 Design**: Modern, adaptive design with light/dark mode support
- **Feature Cards**: Clean grid layout with icons and descriptions
- **Image Selection**: Easy camera/gallery picker with permission handling
- **Responsive Layout**: Optimized for different screen sizes
- **Smooth Animations**: Transitions and loading states

## 📱 Download APK

### Option 1: GitHub Actions (Recommended)
1. Go to the **Actions** tab in this repository
2. Click on the latest workflow run
3. Scroll down to the **Artifacts** section
4. Download either `app-debug-apk` or `app-release-apk`

### Option 2: GitHub Releases
1. Go to the **Releases** page
2. Download the latest APK from the release assets
3. Install on your Android device

### Option 3: Manual Trigger
1. Go to **Actions** → **ImageToolkit APK Workflow**
2. Click **Run workflow**
3. Choose build type (debug/release/both)
4. Optionally create a release
5. Download from artifacts or releases

### Option 4: Local Build
```bash
# Build APKs locally
./build-apk.sh

# Or use the existing build script
./build.sh
```

## 🔧 Development Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ImageToolkit
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the ImageToolkit folder

3. **Sync Project**
   - Android Studio will automatically sync Gradle files
   - Wait for the sync to complete

4. **Run the App**
   - Connect an Android device or start an emulator
   - Click the "Run" button or press Shift+F10

## 📋 Permissions

The app requires the following permissions:
- `CAMERA`: For taking photos
- `READ_EXTERNAL_STORAGE`: For accessing gallery images
- `WRITE_EXTERNAL_STORAGE`: For saving processed images
- `READ_MEDIA_IMAGES`: For Android 13+ media access

## 🏗️ Architecture

### MVVM Pattern
- **Model**: Data classes and repository for image handling
- **View**: Jetpack Compose UI screens
- **ViewModel**: State management and business logic

### Dependency Injection
- **Hilt**: For dependency injection and lifecycle management
- **Repository Pattern**: Centralized data access layer

### Navigation
- **Jetpack Navigation Compose**: Type-safe navigation between screens
- **Deep Linking**: Support for direct feature access

## 🔮 Future Enhancements

1. **Background Removal**: AI-powered background removal
2. **Filters & Effects**: Real-time image filters
3. **Format Conversion**: Convert between JPG, PNG, WebP, etc.
4. **Batch Processing**: Process multiple images at once
5. **Cloud Storage**: Integration with Google Drive, Dropbox
6. **Advanced Editing**: Text overlay, stickers, drawing tools

## 🐛 Known Issues

- App icons are placeholders (need actual PNG files)
- Gradle wrapper jar is placeholder (needs actual file)
- Some Material 3 color references may need adjustment

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📞 Support

For support, email support@imagetoolkit.com or create an issue in the repository.

---

**Built with ❤️ using Jetpack Compose and Material 3**# Workflow Test
