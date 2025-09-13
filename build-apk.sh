#!/bin/bash

# ImageToolkit APK Build Script
# This script builds both debug and release APKs for easy distribution

echo "🚀 ImageToolkit APK Build Script"
echo "================================="

# Check if we're in the right directory
if [ ! -f "settings.gradle" ]; then
    echo "❌ Error: Please run this script from the project root directory"
    exit 1
fi

# Make gradlew executable
chmod +x gradlew

echo "📱 Building ImageToolkit APKs..."

# Clean project
echo "🧹 Cleaning project..."
./gradlew clean

# Check if Android SDK is available
if [ -z "$ANDROID_HOME" ] && [ ! -f "local.properties" ]; then
    echo "⚠️  Android SDK not found locally."
    echo "   This script requires Android Studio or Android SDK to be installed."
    echo "   For automated builds, use GitHub Actions instead."
    echo ""
    echo "🔧 To build locally:"
    echo "   1. Install Android Studio"
    echo "   2. Set ANDROID_HOME environment variable"
    echo "   3. Or create local.properties with sdk.dir"
    echo ""
    echo "📱 For APK downloads, use GitHub Actions:"
    echo "   • Push to main branch to trigger automatic build"
    echo "   • Download APKs from Actions tab"
    exit 0
fi

# Build debug APK
echo "🔨 Building debug APK..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo "✅ Debug APK built successfully!"
    DEBUG_APK_PATH="app/build/outputs/apk/debug/app-debug.apk"
    if [ -f "$DEBUG_APK_PATH" ]; then
        DEBUG_SIZE=$(du -h "$DEBUG_APK_PATH" | cut -f1)
        echo "   📦 Debug APK: $DEBUG_APK_PATH ($DEBUG_SIZE)"
    fi
else
    echo "❌ Debug APK build failed!"
    echo "   Make sure Android SDK is properly configured"
    exit 1
fi

# Build release APK
echo "🔨 Building release APK..."
./gradlew assembleRelease

if [ $? -eq 0 ]; then
    echo "✅ Release APK built successfully!"
    RELEASE_APK_PATH="app/build/outputs/apk/release/app-release.apk"
    if [ -f "$RELEASE_APK_PATH" ]; then
        RELEASE_SIZE=$(du -h "$RELEASE_APK_PATH" | cut -f1)
        echo "   📦 Release APK: $RELEASE_APK_PATH ($RELEASE_SIZE)"
    fi
else
    echo "❌ Release APK build failed!"
    echo "   Make sure Android SDK is properly configured"
    exit 1
fi

echo ""
echo "🎉 Build completed successfully!"
echo ""
echo "📋 APK Files:"
echo "   • Debug APK: app/build/outputs/apk/debug/app-debug.apk"
echo "   • Release APK: app/build/outputs/apk/release/app-release.apk"
echo ""
echo "📱 Installation Instructions:"
echo "   1. Transfer the APK file to your Android device"
echo "   2. Enable 'Install from unknown sources' in Settings"
echo "   3. Open the APK file to install"
echo ""
echo "🔧 For GitHub Actions:"
echo "   • Push to main branch to trigger automatic APK build"
echo "   • Create a version tag (e.g., v1.0.0) to create a release"
echo "   • Download APKs from the Actions tab or Releases page"