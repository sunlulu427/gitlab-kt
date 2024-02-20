#!/bin/sh

# Generate Dokka documentation
./gradlew gitlab:dokkaHtml

# Move to the directory where the dokka html is generated
# shellcheck disable=SC2164
cd build/dokka/html

# Initialize a new git repository
git init

# Add all the files to the new repository
git add .

# Commit the changes
git commit -a -m "Deploy dokka html to github pages"

# Push the changes to the pages branch of your repository
git_remote_info=$(git remote -v | grep origin | grep fetch | awk '{print $2}')
git push -f "${git_remote_info}" origin pages
