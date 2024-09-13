---
title: "How was this blog created"
created: 2024-08-13
---

# [Introduction](/posts/how-it-was-created#intro)

Initially my personal blog page was using the [Jekyll](https://jekyllrb.com/) static site generator.
It was very well-supported by GitHub at that time (Jan 28, 2016).

Recently I wanted to refresh it. Unfortunately, some time ago I reinstalled my local system,
so I do not have Jekyll Gem package installed anymore. Because of my laziness, I decided to create a whole new site
generation engine in Kotlin 😅.
Other reason was that I am more familiar with Kotlin than Ruby and wanted to try something new, especially
since there was the new [2.0 Kotlin](https://kotlinlang.org/docs/whatsnew20.html) version released lately 😉.

To keep it simple, I wanted the new generation engine to work in a similar way as Jekyll.
It is processing the Markdown files and generates HTML code based on those.

In this post I would like to show some technical details of how it was done.

The whole project and repository can be found
**[HERE](https://github.com/PrzemyslawSwiderski/PrzemyslawSwiderski.github.io/)**.

# [Building with Gradle](/posts/how-it-was-created#gradle-build)

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

# [Backend and MD👉HTML conversion](/posts/how-it-was-created#backend)

In order to convert Markdown to HTML files, Kotlin JVM script
[`MdToHtmlConverter.kt`](https://github.com/PrzemyslawSwiderski/PrzemyslawSwiderski.github.io/blob/master/src/jvmMain/kotlin/app/MdToHtmlConverter.kt)
was added. It processes the input catalog and produces the output to the specified directory.
Apart from converting the `.md` files to HTML ones, it copies other files like images and
produces `markdown-metadata.yaml` which is later used for the post's entries generation and routes mapping.

To run the script the `generateHtmlFiles` task was added. It can be executed solely in a standard way by running:

```sh
./gradlew generateHtmlFiles
```

# [Frontend](/posts/how-it-was-created#frontend)

### [Kotlin/JS](/posts/how-it-was-created#kotlinjs)

For the frontend I am using [Kotlin/JS](https://kotlinlang.org/docs/js-overview.html).
I have to admit that initially it was not easy to get familiar with how it is compiled to a JavaScript.
After understanding `external`, `dynamic` concepts and how to import regular JS modules, the development process became
more pleasant.

Since I am familiar with ReactJS and Kotlin/JS has a [support](https://kotlinlang.org/docs/js-react.html) for it,
I decided to choose it as the SPA framework.
JetBrains team is releasing some wrappers for the React e.g. `org.jetbrains.kotlin-wrappers:kotlin-react`.
They have type mappings for the JavaScript and lets the user write type-safe React code in Kotlin like this:

```kotlin
import react.FC
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.nav
import react.dom.html.ReactHTML.ul
import react.router.dom.NavLink

val NavBar = FC {
    nav {
        withClasses("navbar navbar-expand-sm")
        ul {
            withClasses("navbar-nav")
            li {
                asNavItem()
                NavLink {
                    withClasses("nav-link")
                    to = "/"
                    +"About"
                }
            }
            (...)
```

which matches the following Typescript code:

```tsx
import React from "react";

const NavBar: React.FC = () => {
return (
<nav class="navbar navbar-expand-sm">
    <ul class="navbar-nav">
        <li class="nav-item">
            <NavLink class="nav-link active" to="/">About</NavLink>
        </li>
</nav>;
);
};
```

This is pretty good alternative for someone who do not like XML tags (like me 😅) and finds it easier to read.
It is also quite powerful with Kotlin extension function like `withClasses()` and `asNavItem()` in the example.
AFAIK it is not possible in JS or Typescript to do such things that easily.

### [Bootstrap](/posts/how-it-was-created#bootstrap)

In order to make the application responsive Bootstrap and Sass toolkit was used.
The integration wasn't so smooth and I spent some time on setting up the Webpack config.
Instead of the separate CSS modules I wanted to make a single `main.css` which I could include in `index.html`.

Initially I thought Kotlin Multiplatform plugin would help me with it, unfortunately it is not possible and its
KotlinDSL options does not allow such things. I had to create a custom `webpack.config.d/loaders.config.js`
and add `css`, `sass` etc. dependencies manually.

After some hard time spent while setting this up, I was able to make `src/jsMain/resources/scss` to be bundled into
a single CSS file 🥳.

### [GitHub Pages SPA support](/posts/how-it-was-created#gh-pages)

Since GitHub Pages does not support SPA apps out of the box some additional actions were needed.
I used the existing solution found [here](https://github.com/rafgraph/spa-github-pages).
Some additional JS scripts and custom `404.html` page was needed, but I managed to `<BrowserRouter/>` React component
work properly.

# [Common](/posts/how-it-was-created#common)

Using Kotlin Multiplatform setup can be also useful if we would like to define a common Frontend and Backend components.
In this project I created
[`MdMetadata.kt`](https://github.com/PrzemyslawSwiderski/PrzemyslawSwiderski.github.io/blob/master/src/commonMain/kotlin/app/model/MdMetadata.kt)
file which is the model class for the metadata output from Backend and the same is being used as the input to a
frontend router and HTML imports.

Serialization/Deserialization was done thanks to Kotlinx serialization dependency compatible for both JVM and JS parts.

# [Development](/posts/how-it-was-created#development)

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

# [CI/CD](/posts/how-it-was-created#ci-cd)

On each push to master the GitHub action specified
in [gh-pages.yml](https://github.com/PrzemyslawSwiderski/PrzemyslawSwiderski.github.io/blob/master/.github/workflows/gh-pages.yml)
is invoked. It builds project with Gradle and deploys new static resource by simply pushing output files to `gh-pages`
branch. The push/commit is executed by [`peaceiris/actions-gh-pages`](https://github.com/peaceiris/actions-gh-pages)
action.

Cool thing is that you can edit MD files directly from GitHub site:

<img class="responsive-img" src="/pages/posts/how-it-was-created/github-edit.png" alt="Edit MD file directly"></img>

and see the changes in a few minutes after
commiting the changes to master.

# [Conclusions](/posts/how-it-was-created#conclusions)

To sum up, because of the Gradle and its Kotlin (both JVM and JS) support I was able to create basic Jekyll like Single
Page Application template which lets the user to easily manage the blog entries and structure.

To be fair I am not sure yet whether I would use Kotlin/JS in more complex enterprise scenarios, but I definitely see
some potential in terms of making JS frontend apps easier to maintain and less error-prone.

I also need to admit that both Kotlin and Gradle are becoming better in terms of performance and development experience
with each new version. Intellij IDE support for these tools is also priceless.
I feel like the autocompletion, compilation times and development experience improved a lot comparing to some older
2018 versions. I don't have the exact numbers but with the new Kotlin 2.0
[improvements](https://blog.jetbrains.com/kotlin/2024/05/celebrating-kotlin-2-0-fast-smart-and-multiplatform/)
I can confirm that it is noticeably faster.

Project can be found in the following
[repo](https://github.com/PrzemyslawSwiderski/PrzemyslawSwiderski.github.io/).
Feel free to use it as the template for the personal blog or some more sophisticated case 😄.
