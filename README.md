# When Scheduler app
When scheduler is a small web-application that allows for users to create and schedule events with other users.
First selecting open dates and then voting on the best date the users can more easily agree on event dates.

Goal of the app is to take excessive messaging and back-and-forth out of scheduling events and let the users focus on what really matters - the event itself.

## Current status:
non-functional: Development in progress.

## Development
Scheduler is created using clojure and helix.core library for the frontend written with clojurescript and producing react website.

Development and project dependencies are managed using nix flakes and nix compatible packages. After nix installs the tools, task file is used to manage and run the project itself.

### Requirements
- nix
- nix flake
- direnv
- task
