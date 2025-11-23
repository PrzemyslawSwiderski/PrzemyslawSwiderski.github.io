---
title: "Giscus comments in KoltinJS"
created: 2025-01-04
readTime: "6 mins"
---

{toc.placeholder}

## Intro

In order to improve this blog, I decided to add comments section at the bottom of the posts.

I remember I used Disqus plugin for the previous blog version in Jekyll.
However, I encountered this [post](https://donw.io/post/github-comments/), aaaand
it looks like Disqus is doing a lot of (not necessarily privacy-friendly 😅) stuff behind the curtain.

I was looking for some alternative and found one recommendation on Redit which is
**[Giscus](https://github.com/giscus/)**.

I gave it a try, the whole integration went smoothly and was completed in less than an hour 😘.

## Integration

To integrate the Giscus I followed pretty useful instruction available [here](https://giscus.app/).

What I did was as follows:

1. Added [`giscus`](https://github.com/apps/giscus) app to the GitHub account.
2. Turn on the "Discussion" feature in my `PrzemyslawSwiderski/PrzemyslawSwiderski.github.io` repository.
3. Added `implementation(npm("@giscus/react", libs.versions.npm.giscus.get()))` dependency to `build.gradle.kts` with
   the latest version defined in `libs.versions.toml`.
4. Rebuilt the blog to install new package.
5. Added `Giscus.kt` file with the new React component Kotlin mapping and definition.
6. Added new `Giscus { ... }` element to a `SinglePost` existing component.
7. Launched the React blog app.
8. Enjoyed the new comments section being added 😊

![Comments](giscus-test-comment.png)

## Conclusions

Implementation part was pretty easy and straightforward.
The hardest bit for me was to include the external React component in KotlinJS, but I found some help on Stack 😉.

To my surprise, Giscus offers the reactions feature out of the box.

The cool thing is also that discussions can be managed at the repository "Discussion" tab level.

The main drawback coming to my mind is that, the visitor must have the GitHub account to perform any
modification action. However, since my blog is heavily technical one I hope it is not that huge issue.  
