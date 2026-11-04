---
title: "CV as a Code"
created: 2026-11-04
readTime: "3 mins"
---


For a long time I was thinking about how to create my CV in a way that it can be easily maintained and updated.
I also wanted to have it in a LaTeX format to look professional and be able to generate a PDF version of it.
For this purpose I created a repository on GitHub
called [pswidersk-cv](https://github.com/PrzemyslawSwiderski/pswidersk-cv).

It contains a Gradle project that generates the output CV files by using Python and [Pandoc](https://pandoc.org/) tool.
Build file is written in Gradle Kotlin DSL and contains a
[`python-uv-plugin`](https://github.com/PrzemyslawSwiderski/python-uv-gradle-plugin) which takes care
of the entire Python and virtual environment setup.
All is required is a JRE and the user can generate output files by running the `pandocRun` Gradle task:

```bash
./gradlew pandocRun
```

The input files are written in Markdown format and the output files are generated in PDF, DOCX, and other formats.
I also created a GitHub Action that automatically (on commit) generates the CV files and uploads them as the latest
repository's release artifacts.
The PDF version of the CV can be downloaded from the following
**[link](https://github.com/PrzemyslawSwiderski/pswidersk-cv/releases/download/latest/Przemyslaw_Swiderski_CV.pdf)**.

This way I can easily maintain and update my CV by simply modifying the input files in the repository and committing the
changes.
If you would like to create your own CV in a similar way, feel free to fork the repository and modify the input files to
your liking ❤️.
