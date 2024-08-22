---
title: "How this blog was created"
created: 2024-08-13
---

## Introduction

Initially my personal blog page was using the [Jekyll](https://jekyllrb.com/) static site generator.
It was very well-supported by GitHub at that time (Jan 28, 2016).

Lately I decided to refresh it. I reinstalled my local system, so I did not have Jekyll Gem package installed anymore.
So because of my laziness, I created a whole new site generation engine in Kotlin 😅.
Other reason was simply that I am more familiar with Kotlin than Ruby and wanted to try something new especially
since there was the new [2.0 Kotlin](https://kotlinlang.org/docs/whatsnew20.html) version released 😉.

To keep it simple, I wanted the new generation engine to work in a similar way as Jekyll.
It is processing the Markdown files and generates HTML code based on those.

In this post I would like to show some technical details of how it was done 🙂.

The whole project and repository can be found
**[HERE](https://github.com/PrzemyslawSwiderski/PrzemyslawSwiderski.github.io/)**.

## Building with Gradle

[Gradle](https://gradle.org/) is the build system [widely used](https://www.jetbrains.com/lp/devecosystem-2023/java/) in
many JVM projects.
Since it is supporting writing build scripts with the Kotlin DSL and has got out off the box Kotlin plugins
it was pretty clear to use it for the Kotlin project.

What I like the most is that only the Java RE is required to run the build.
It can be invoked by simply calling:

* `./gradlew build` on Unix systems

* or `gradlew.bat build` on Windows

in the project directory.

All Java dependencies, Node.js, and NPM packages will be installed in the background.

## Backend and MD 👉 HTML conversion

In order to convert Markdown to HTML files, Kotlin JVM script
[`MdToHtmlConverter.kt`](https://github.com/PrzemyslawSwiderski/PrzemyslawSwiderski.github.io/blob/master/src/jvmMain/kotlin/app/MdToHtmlConverter.kt)
was added. It processes the input catalog and produces the output to the specified directory.
Apart from converting the `.md` files to HTML ones, it copies other files like images and
produces `markdown-metadata.yaml` which is later used for the post's entries generation and routes mapping.

To run the script the `generateHtmlFiles` task was added. It can be executed solely in a standard way by running:

```commandline
./gradlew generateHtmlFiles
```

## Frontend

IN PROGRESS

## Development

For the local development there is a separate `jsRun` Gradle task.

By executing `./gradlew jsRun -t` Gradle is running the development server locally and rebuilds the application if the
source files changes.

Changes are visible in few seconds:

<img class="responsive-img" src="/pages/posts/how-it-was-created/live-changes.gif" alt="Live reload"></img>

As it can be seen in the `jsRun` task's logs, changes done in `content.md` file triggered rebuilding because the
whole `pages` catalog was specified as the input of `generateHtmlFiles` task in
[build.gradle.kts](https://github.com/PrzemyslawSwiderski/PrzemyslawSwiderski.github.io/blob/master/build.gradle.kts)
file.
Gradle is somehow able to figure out that the build cache is invalid and due to dependency on `jsProcessResources` task:

```kotlin
named("jsProcessResources") {
    dependsOn(generateHtmlFiles)
}
```

it detects changes and rebuilds the app.

It is pretty impressive that we can execute some Kotlin JVM code with `generateHtmlFiles` task and see the output on
frontend almost instantly 😎.

## CI/CD

On each push to master the GitHub action specified
in [gh-pages.yml](https://github.com/PrzemyslawSwiderski/PrzemyslawSwiderski.github.io/blob/master/.github/workflows/gh-pages.yml)
is invoked. It builds project with Gradle and deploys new static resource by simply pushing output files to `gh-pages`
branch. The push/commit is executed by [`peaceiris/actions-gh-pages`](https://github.com/peaceiris/actions-gh-pages)
action.

Cool thing is that you can edit MD files directly from GitHub site:

<img class="responsive-img" src="/pages/posts/how-it-was-created/github-edit.png" alt="Edit MD file directly"></img>

and see the changes in a few minutes after
commiting the changes to master.
