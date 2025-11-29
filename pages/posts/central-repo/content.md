---
title: "Maintaining multiple GitHub repos at once"
created: 2025-11-26
readTime: "7 mins"
---

## Problem

At the time of writing this post, I have 62 GitHub repositories.  
With monorepos gaining popularity,
I've noticed that the common practice is to create a new repository for each new project instead of nesting it within an
existing collective one. Most of my repositories are, of course, no longer active 😅.
Nevertheless, there are still 8 projects I want to actively maintain.

I wanted to find a generic solution that requires as little manual effort as possible 🙂.

The solution should primarily do three things:

1. Use common configuration and workflow files stored in a single place.
2. Automatically update dependencies.
3. Auto releases new versions periodically.

Below, I'll describe how I achieved this.

## Central source of configuration and workflow files

To have a single source of 'truth' I found a very cool files sync
GitHub [action](https://github.com/wadackel/files-sync-action).
It basically synchronizes files across multiple repositories based on a YAML configuration file.
By synchronizing, I mean, that it can create PRs and merge them automatically.

In order to use it and apply to my case, I created a
central repository in **[here](https://github.com/PrzemyslawSwiderski/central-repo/tree/main)**.
It contains the action workflow, files-sync config files and of course the files I want to reuse across the projects.
For my Gradle plugin projects I have the following `files-sync-config.yaml`:

```yml
settings:
  pull_request:
    labels:
      - "files-sync" # Labels to add for the PRs
    merge:
      mode: auto # auto merge PRs once required checks pass
      strategy: squash # squash before merging
      delete_branch: true # always remove the branch

patterns:
  - files:
      - from: commons/.github
        to: .github
      - from: gradle-plugins
        to: ./
        exclude:
          - files-sync-config.yaml
      - from: gradle-plugins/.github # dotfiles needs to be specified explicitly (https://github.com/wadackel/files-sync-action/issues/215)
        to: .github
    repositories: # repositories which will be synchronized
      - PrzemyslawSwiderski/python-gradle-plugin
      - PrzemyslawSwiderski/python-uv-gradle-plugin
      - PrzemyslawSwiderski/terraform-gradle-plugin
      - PrzemyslawSwiderski/helm-gradle-plugin
      - PrzemyslawSwiderski/yaml-secrets-gradle-plugin
```

Important to mention is that for the auto merging to work, it is required to allow it in each repository settings.
Same applies to squash and delete branch.

Now, if for instance I change `commons/.github/release-drafter.yml` file in `main` branch, action will be invoked.
Progress of it can be checked in "Actions" tab.
The logs of the action can indicate whether the synchronization was successful or not.
They are pretty descriptive and look like this:

```log
> Run wadackel/files-sync-action@v3
	
Synchronize 6 files:
	
           Repository: PrzemyslawSwiderski/python-gradle-plugin
               Branch: files-sync/PrzemyslawSwiderski-central-repo-0
           Branch SHA: 00adb0a998cb799f08d2e54ee8fec198c52bca3e
           Commit SHA: 3fbc93bd44fbb7fd925ea049a5b17ac348b9ba56
               Commit: "chore: sync files with `PrzemyslawSwiderski/central-repo`"
        Changed Files: 1
         Pull Request: https://github.com/PrzemyslawSwiderski/python-gradle-plugin/pull/67
               Labels: files-sync
            Reviewers: None
            Assignees: None
   Pull Request Merge: The pull request was set to auto-merge.
               Status: Complete

(...)	
```

## Dependencies management

My first thought was to use GitHub's
native [Dependabot](https://docs.github.com/en/code-security/getting-started/dependabot-quickstart-guide).
After exploring [Renovate](https://github.com/renovatebot/renovate), however, I decided to go with the latter.

The main advantages of Renovate over Dependabot are:

1. Open source 🥰.
2. Rich dashboard with better observability.
3. Significantly more powerful and capable
   (it even updates plugin versions for examples in my `settings.gradle.kts` files!)
4. Can group updates into a single PR.
5. Highly [configurable](https://docs.renovatebot.com/config-overview/) via `renovate.json` with presets.
6. Better scheduled auto merging.
7. Is platform-agnostic, can work on GitLab, Bitbucket and more.

Because Renovate is pretty versatile and supports [presets](https://docs.renovatebot.com/key-concepts/presets/),
the `renovate.json` for my 5 Gradle plugins can look like that:

```json
{
  "$schema": "https://docs.renovatebot.com/renovate-schema.json",
  "extends": [
    ":dependencyDashboard",
    ":semanticPrefixFixDepsChoreOthers",
    ":ignoreModulesAndTests",
    "group:monorepos",
    "group:recommended",
    "mergeConfidence:age-confidence-badges",
    "replacements:all",
    "workarounds:all",
    ":automergeAll",
    ":automergeRequireAllStatusChecks"
  ],
  "github-actions": {
    "enabled": false
  },
  "ignorePaths": [
    "examples/**"
  ],
  "minimumReleaseAge": "2 days",
  "prCreation": "not-pending"
}
```

I also added `minimumReleaseAge` and `prCreation` fields to not create a PRs for the dependencies newer than 2 days.
It is to make sure the probability of no quick fixes is higher.

## Auto release

For the auto releasing I am using the [Release Drafter](https://github.com/marketplace/actions/release-drafter) action
and custom
[release](https://github.com/PrzemyslawSwiderski/central-repo/blob/main/gradle-plugins/.github/workflows/auto-release.yml)
job workflow.

Release Drafter is pretty useful for the changelog creation. It also bumps the semantic versions based on the PR labels
or branch names. It is fully configurable with `release-drafter.yml` file which can also be common for all repos due to
my central repo setup.

The release workflow is configured to eun every Tuesday or manually if needed.
It basically finds the last draft release, builds it and publishes to remote.

## Summary

The central repository approach saves me a lot of time. I no longer need to manually commit common files to each repo. I
can simply modify a single **central** repo.  
Updating dependencies and releasing new versions is fully automated, and manual work is required only when a PR's checks
fail.

The entire setup has been running successfully for about two weeks, and I'm pretty happy with it 🐱.
