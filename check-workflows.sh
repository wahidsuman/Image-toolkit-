#!/bin/bash

# Workflow Status Checker
# This script helps check the status of GitHub Actions workflows

echo "🔍 ImageToolkit Workflow Status Checker"
echo "======================================="

# Check if we're in a git repository
if [ ! -d ".git" ]; then
    echo "❌ Error: Not in a git repository"
    exit 1
fi

# Get repository URL
REPO_URL=$(git remote get-url origin 2>/dev/null)
if [ -z "$REPO_URL" ]; then
    echo "❌ Error: No remote repository found"
    echo "   Make sure you've pushed to GitHub first"
    exit 1
fi

echo "📱 Repository: $REPO_URL"
echo ""

# Check if workflows exist
if [ -d ".github/workflows" ]; then
    echo "✅ Workflows found:"
    ls -la .github/workflows/*.yml | while read line; do
        filename=$(echo $line | awk '{print $NF}' | sed 's/.*\///')
        echo "   • $filename"
    done
else
    echo "❌ No workflows found in .github/workflows/"
    exit 1
fi

echo ""
echo "🚀 Workflow Status:"
echo "   • Single APK Workflow: Handles everything"
echo "   • Auto-trigger: Push to main, pull requests"
echo "   • Manual trigger: With build type selection"
echo "   • Release creation: On version tags or manual"
echo ""

echo "📥 How to Download APKs:"
echo "   1. Go to: https://github.com/$(echo $REPO_URL | sed 's/.*github.com[:/]\([^.]*\).*/\1/')/actions"
echo "   2. Click on the latest workflow run"
echo "   3. Scroll to 'Artifacts' section"
echo "   4. Download the APK file(s)"
echo ""

echo "🔧 Manual Trigger:"
echo "   1. Go to Actions tab in GitHub"
echo "   2. Select 'ImageToolkit APK Workflow'"
echo "   3. Click 'Run workflow'"
echo "   4. Choose build type (debug/release/both)"
echo "   5. Optionally create a release"
echo ""

echo "📋 Next Steps:"
echo "   • Push changes to trigger automatic builds"
echo "   • Create version tags for releases: git tag v1.0.0 && git push origin v1.0.0"
echo "   • Check workflow status in GitHub Actions tab"