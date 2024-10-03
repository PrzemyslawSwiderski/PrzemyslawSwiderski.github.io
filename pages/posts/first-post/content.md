---
title: "My First Test Post"
created: 2024-08-07
---

## Hi there!!! This is my first post on my new blog. 

### It is mainly to check Markdown processing so can be a little messy 😄.

<br/>

### With some fancy GitHub logo ![GitHub](/pages/posts/first-post/github-logo.svg "GitHub")

<br/>

### Sample Kotlin code

```kotlin
print("Hello World from Kotlin :D")

private fun tryToParse(yamlString: String): MdMetadata {
    try {
        return Yaml.default.decodeFromString<MdMetadata>(yamlString)
    } catch (ex: Exception) {
        println("Exception during YAML parsing: ${ex.message}")
        return MdMetadata()
    }
}
```

<br/>

### List

* item 1
* item 2

<br/>

### Video

<br/>

<img class="responsive-img" src="/pages/posts/first-post/giphy.gif"></img>