"use strict";(this.webpackChunkpswidersk_page=this.webpackChunkpswidersk_page||[]).push([[4375],{4375:e=>{e.exports='<ul><li><a href="/posts/sdk-import-plugin-update#introduction"> Introduction</a></li><li><a href="/posts/sdk-import-plugin-update#updating-to-intellij-platform-gradle-plugin"> Updating to IntelliJ Platform Gradle Plugin</a><ul><li><a href="/posts/sdk-import-plugin-update#kover"> Kover</a></li><li><a href="/posts/sdk-import-plugin-update#junit-5-problem"> Junit 5 problem</a></li><li><a href="/posts/sdk-import-plugin-update#missing-runpluginverifier-task"> Missing <code>runPluginVerifier</code> task</a></li><li><a href="/posts/sdk-import-plugin-update#gradle-build-file-autocompletion"> Gradle build file autocompletion</a></li><li><a href="/posts/sdk-import-plugin-update#intellij-platform-gradle-plugin-update-summary"> IntelliJ Platform Gradle Plugin update summary</a></li></ul></li><li><a href="/posts/sdk-import-plugin-update#code-changes"> Code changes</a><ul><li><a href="/posts/sdk-import-plugin-update#summary"> Summary</a></li></ul></li></ul><h1>Introduction <a id="introduction" href="/posts/sdk-import-plugin-update#introduction" class="anchor-link">🔗</a></h1><p>Some time ago I created\nthe <a href="https://plugins.jetbrains.com/plugin/24223-sdk-import/edit/versions/stable/600376">SDK-Import</a> IntelliJ Plugin.\nUnfortunately it depends on <code>PythonCore:231.8109.2</code> plugin which is not supporting the new <code>2024.2+</code> IntelliJ versions.\nBecause of that I had to update it to the latest one and the SDK-Import plugin itself.</p><p>The changes can be viewed in this <a href="https://github.com/PrzemyslawSwiderski/sdk-import-plugin/pull/45">PR</a>.</p><p>In this post I will try to describe how I did it.</p><h1>Updating to IntelliJ Platform Gradle Plugin <a id="updating-to-intellij-platform-gradle-plugin" href="/posts/sdk-import-plugin-update#updating-to-intellij-platform-gradle-plugin" class="anchor-link">🔗</a></h1><p>As noted\nin <a href="https://blog.jetbrains.com/platform/2024/07/intellij-platform-gradle-plugin-2-0/#the-importance-of-updating">this</a>\nlink, there are some breaking layout changes introduced in a new IntelliJ version.</p><p>In order to support new IntelliJ versions I had to migrate project build from the old <code>Gradle IntelliJ Plugin</code> to\n<code>IntelliJ Platform Gradle Plugin</code>.\nThere is some <a href="https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin-migration.html">guide</a>\nprovided about how to do that.</p><p>Nevertheless, I faced some issues while doing it.</p><h2>Kover <a id="kover" href="/posts/sdk-import-plugin-update#kover" class="anchor-link">🔗</a></h2><p>After changing gradle plugin references in <code>build.gradle.kts</code> to <code>IntelliJ Platform Gradle Plugin</code> the tests started to\nfailing with the following message:</p><pre><code class="language-text">Could not initialize property keys deprecation map because DeprecatedRuntimeConstants.__$hits$__ field isn\'t properly named\n</code></pre><p>To fix it I had to follow the advice\nfrom <a href="https://github.com/JetBrains/intellij-platform-gradle-plugin/issues/1702">here</a> and\nchange <code>kover</code> settings block to following:</p><pre><code class="language-kotlin">kover {\n    currentProject {\n        instrumentation {\n            excludedClasses.add(&quot;org.apache.velocity.*&quot;)\n        }\n    }\n}\n</code></pre><h2>Junit 5 problem <a id="junit-5-problem" href="/posts/sdk-import-plugin-update#junit-5-problem" class="anchor-link">🔗</a></h2><p>As described\nin <a href="https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin-faq.html#junit5-test-framework-refers-to-junit4">link</a>\nwhen using <code>testFramework(TestFrameworkType.JUnit5)</code> the tests are failing because of <code>NoClassDefFoundError</code>.\nI fixed it in my project by adding <code>testImplementation(&quot;org.junit.vintage:junit-vintage-engine:${libs.versions.junit}&quot;)</code>\nto the dependencies section.</p><h2>Missing <code>runPluginVerifier</code> task <a id="missing-runpluginverifier-task" href="/posts/sdk-import-plugin-update#missing-runpluginverifier-task" class="anchor-link">🔗</a></h2><p>The new plugin uses <code>verifyPlugin</code> instead so I had to update <code>.github/workflows/build.yml</code> file to run the build\nsuccessfully.</p><h2>Gradle build file autocompletion <a id="gradle-build-file-autocompletion" href="/posts/sdk-import-plugin-update#gradle-build-file-autocompletion" class="anchor-link">🔗</a></h2><p>One issue I struggled to fix were so-called red lines in gradle build files (<code>build.gradle.kts</code>):</p><p><img src="/pages/posts/sdk-import-plugin-update/red-lines-gradle.png" alt="Red lines in build script" class="markdown-img" /></p><p>It seems like IDE could not resolve some Kotlin dependencies and autocompletion IntelliJ feature was not working because\nof that.\nEven though Gradle tasks are executed successfully it was pretty annoying that Intellij was complaining.</p><p>What finally resolved the issue 🤩 was removal of <code>.idea</code>, <code>build</code> and <code>.intellijPlatform</code>\ndirectories and restarting the whole project.</p><h2>IntelliJ Platform Gradle Plugin update summary <a id="intellij-platform-gradle-plugin-update-summary" href="/posts/sdk-import-plugin-update#intellij-platform-gradle-plugin-update-summary" class="anchor-link">🔗</a></h2><p>Overall the migration went pretty smooth.\nMigration <a href="https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin-migration.html">guide</a> was\npretty clear and I also used the updated <a href="https://github.com/JetBrains/intellij-platform-plugin-template">template</a>\nrepository to check how the new reference files should look like.</p><p>Hopefully the temporary fixes I\nmentioned (<a href="/posts/sdk-import-plugin-update#junit-5">Junit 5</a>, <a href="/posts/sdk-import-plugin-update#kover">Kover</a>) will not\nbe necessary in the next plugin version and redundant code can be removed.</p><h1>Code changes <a id="code-changes" href="/posts/sdk-import-plugin-update#code-changes" class="anchor-link">🔗</a></h1><p>The source code of the plugin itself was not needed to be adjusted.\nHowever, the testing framework had some breaking changes in the new <code>2024.2</code> version.</p><p>Necessary changes in test source code:</p><ul><li>changing <code>@RunInEdt</code> to <code>@RunInEdt(writeIntent = true)</code></li><li>replacing rules<pre><code class="language-kotlin">@JvmField\n@RegisterExtension\nval projectModel: ProjectModelExtension = ProjectModelExtension()\nprivate val project: Project\n    get() = projectModel.project\n</code></pre><p>with fixtures helper functions</p><pre><code class="language-kotlin">private val projectModel = customProjectFixture()\nprivate val project: Project\n    get() = projectModel.get()\n</code></pre></li></ul><p>After that the tests were compiling and green.</p><h2>Summary <a id="summary" href="/posts/sdk-import-plugin-update#summary" class="anchor-link">🔗</a></h2><p>Update went successfully.\nThe new <code>1.2.0</code> SDK-Import plugin was deployed and\nis <a href="https://plugins.jetbrains.com/plugin/24223-sdk-import/">ready</a> to be used in the new <code>2024.2</code>\nIntelliJ releases 🙂.</p>'}}]);