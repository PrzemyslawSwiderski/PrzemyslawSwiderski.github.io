---
layout: post
title: Using Kotlin DSL in Gradle builds
date: 2020-04-15 20:00:00 +0000
comments: true
categories: []

---
In this post I would like to present my experience with Gradle and Kotlin DSL.

### Intro

Gradle is a build tool which enables to compile Java projects, however developers can also write custom tasks or perform additional complex actions by writing Gradle build scripts.

Although Maven build system is still more popular, Gradle gains popularity in \[last years\]([https://www.jetbrains.com/lp/devecosystem-2019/java/](https://www.jetbrains.com/lp/devecosystem-2019/java/ "https://www.jetbrains.com/lp/devecosystem-2019/java/")).

Since version 5.0 Gradle now supports Kotlin DSL (except Groovy) as build scripts.

### Sample project configuration

### Writing custom task

### Debugging

### Summary

Personally, I worked with older build tools such as Maven or Ant and in my opinion Gradle is a way better. Writing scripts in Kotlin code instead of XML elements can improve readability and maintainability of build config. It is also possible to print to standard output some configuration variables or even debug Kotlin DSL code. Such as flexibility was not possible in case of older build systems. 