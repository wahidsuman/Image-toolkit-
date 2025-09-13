# 📱 ImageToolkit APK Workflow

This repository uses a single comprehensive GitHub Actions workflow to build and distribute APK files.

## 🚀 Workflow Features

### **Single Workflow** (`apk-workflow.yml`)
- **One file handles everything**: Builds, releases, and manual triggers
- **Smart triggers**: Automatic on push/PR, manual with options
- **Flexible builds**: Choose debug, release, or both APKs
- **Release creation**: Automatic on tags, optional on manual trigger

## 🔧 How to Use

### **Automatic Builds**
- **Push to main**: Builds both debug and release APKs
- **Pull requests**: Builds both APKs for testing
- **Version tags**: Creates GitHub release with APK files

### **Manual Trigger**
1. Go to **Actions** tab
2. Select **"ImageToolkit APK Workflow"**
3. Click **"Run workflow"**
4. Choose options:
   - **Build type**: debug, release, or both
   - **Create release**: Optional GitHub release creation

### **Download APKs**

#### **From Artifacts** (Most Common)
1. Go to [Actions tab](https://github.com/wahidsuman/Image-toolkit-/actions)
2. Click on latest workflow run
3. Scroll to **"Artifacts"** section
4. Download `app-debug-apk` or `app-release-apk`

#### **From Releases**
1. Go to [Releases page](https://github.com/wahidsuman/Image-toolkit-/releases)
2. Download APK from latest release
3. Install on Android device

## 📋 Workflow Triggers

| Trigger | Build Type | Creates Release | Use Case |
|---------|------------|-----------------|----------|
| Push to main | Both | No | Development builds |
| Pull request | Both | No | Testing |
| Version tag | Both | Yes | Production releases |
| Manual (debug) | Debug only | Optional | Testing specific build |
| Manual (release) | Release only | Optional | Production testing |
| Manual (both) | Both | Optional | Full build |

## 🛠️ Local Development

### **Build Scripts**
```bash
# Check workflow status
./check-workflows.sh

# Build locally (requires Android Studio)
./build-apk.sh

# Original build script
./build.sh
```

### **Requirements**
- Android Studio for local builds
- GitHub account for automated builds
- Android device for testing

## 📱 APK Information

- **App Name**: ImageToolkit
- **Package**: com.imagetoolkit.app
- **Version**: 1.0
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## 🔍 Troubleshooting

### **Workflow Not Running**
- Check if workflows are enabled in repository settings
- Ensure you have push access to the repository
- Verify the workflow file is in `.github/workflows/`

### **Build Failures**
- Check the Actions tab for error details
- Ensure all dependencies are properly configured
- Verify Android SDK setup in workflow
- **Deprecated Actions**: Workflow uses latest action versions (v4)

### **Download Issues**
- Artifacts expire after 30 days
- Check if the workflow run completed successfully
- Try downloading from Releases instead

### **Action Deprecation Errors**
- ✅ **Fixed**: Updated to `actions/upload-artifact@v4`
- ✅ **Fixed**: Updated to `actions/cache@v4`
- ✅ **Fixed**: Replaced `actions/create-release` with `softprops/action-gh-release`

## 📞 Support

For issues with the workflow or APK builds:
1. Check the [Actions tab](https://github.com/wahidsuman/Image-toolkit-/actions) for error logs
2. Create an issue in the repository
3. Check the workflow documentation above

---

**Built with ❤️ using GitHub Actions**