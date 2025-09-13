#!/bin/bash

# ImageToolkit Build Script
# This script helps build and run the Android app

echo "🚀 ImageToolkit Build Script"
echo "=============================="

# Check if we're in the right directory
if [ ! -f "settings.gradle" ]; then
    echo "❌ Error: Please run this script from the project root directory"
    exit 1
fi

# Make gradlew executable
chmod +x gradlew

echo "📱 Building ImageToolkit..."

# Clean and build
echo "🧹 Cleaning project..."
./gradlew clean

echo "🔨 Building project..."
./gradlew build

if [ $? -eq 0 ]; then
    echo "✅ Build successful!"
    echo ""
    echo "📋 Next steps:"
    echo "1. Open the project in Android Studio"
    echo "2. Connect an Android device or start an emulator"
    echo "3. Click 'Run' to install and launch the app"
    echo ""
    echo "🎯 Features available:"
    echo "   • Image selection from Camera/Gallery"
    echo "   • Crop & Resize with uCrop"
    echo "   • Placeholder screens for future features"
else
    echo "❌ Build failed. Please check the error messages above."
    exit 1
fi