import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

plugins.withType<YarnPlugin> {
    configure<YarnRootExtension> {
        yarnLockAutoReplace = true
    }
}

kotlin {
    jvm()
    js {
        browser {
            commonWebpackConfig {

            }
        }
        binaries.executable()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.datetime)
            }
        }

        jvmMain {
            dependencies {
                implementation(libs.kaml.jvm)
                implementation(libs.markdown.jvm)
                implementation(libs.jfree.svg)
            }
        }

        jvmTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        jsMain {
            dependencies {
                implementation(kotlinWrappers.react)
                implementation(kotlinWrappers.reactDom)
                implementation(kotlinWrappers.reactUse)
//                implementation(kotlinWrappers.reactRouterDomLegacy)
                implementation(kotlinWrappers.tanstack.reactRouter)
                implementation(kotlinWrappers.js)
                implementation(kotlinWrappers.jsCore)
                implementation(npm("bootstrap", libs.versions.npm.bootstrap.get()))
                implementation(npm("yaml-loader", libs.versions.npm.loader.yaml.get()))
                implementation(npm("css-loader", libs.versions.npm.loader.css.get()))
                implementation(npm("sass-loader", libs.versions.npm.loader.sass.get()))
                implementation(npm("sass", libs.versions.npm.sass.get()))
                implementation(npm("mini-css-extract-plugin", libs.versions.npm.minicss.get()))
                implementation(npm("share-buttons", libs.versions.npm.sharebuttons.get()))
                implementation(npm("highlight.js", libs.versions.npm.highlightjs.get()))
                implementation(npm("highlightjs-copy", libs.versions.npm.highlightjsCopyPlugin.get()))
                implementation(npm("@giscus/react", libs.versions.npm.giscus.get()))
            }
        }
    }
}

tasks {
    val jvmRuntimeClasspath by configurations
    val kotlinClasspath = layout.buildDirectory.files("classes/kotlin/jvm/main") + jvmRuntimeClasspath
    val jsResourcesPath = layout.buildDirectory.file("processedResources/js/main")

    val generateHtmlFiles by registering(JavaExec::class) {
        val inputDir = "pages"
        group = "run"
        mainClass = "app.MdToHtmlConverterKt"
        classpath = kotlinClasspath
        val outputPath = jsResourcesPath.get().asFile.absolutePath
        environment = mapOf(
            "INPUT_DIR" to inputDir,
            "OUTPUT_DIR" to outputPath
        )
        inputs.dir(inputDir)
        outputs.dir(outputPath)
        dependsOn("compileKotlinJvm")
    }

    val generateVoronoi by registering(JavaExec::class) {
        group = "run"
        mainClass = "app.VoronoiGeneratorKt"
        classpath = kotlinClasspath
        val outputPath = jsResourcesPath.get().asFile.absolutePath
        environment = mapOf(
            "OUTPUT_DIR" to outputPath
        )
        outputs.dir(outputPath)
        dependsOn("compileKotlinJvm")
    }

    named("jsProcessResources") {
        dependsOn(generateHtmlFiles, generateVoronoi)
    }

}