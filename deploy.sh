#!/bin/sh
set -ex

# Clean first
./gradlew clean

# Get remote info
git_remote_info=$(git remote -v | grep origin | grep fetch | awk '{print $2}')
git_commit=$(git rev-parse --short HEAD)
# Generate Dokka documentation
./gradlew gitlab:dokkaHtml

# Move to the directory where the dokka html is generated
# shellcheck disable=SC2164
cd gitlab/build/dokka/html

# Initialize a new git repository
git init

# Add all the files to the new repository
git add .

# Commit the changes
git commit -a -m "Deploy dokka html to github pages @${git_commit}"

# Push the changes to the pages branch of your repository
git_branch=$(git rev-parse --abbrev-ref HEAD)
git push -f "${git_remote_info}" "${git_branch}:pages"
