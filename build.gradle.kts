import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

repositories {
    mavenCentral()
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
    }.binaries.executable()

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
            }
        }

        jsMain {
            dependencies {
                implementation(dependencies.platform(libs.kotlin.wrappers))
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom")
                implementation(devNpm("bootstrap", libs.versions.npm.bootstrap.get()))
                implementation(devNpm("yaml-loader", libs.versions.npm.loader.yaml.get()))
                implementation(devNpm("css-loader", libs.versions.npm.loader.css.get()))
                implementation(devNpm("sass-loader", libs.versions.npm.loader.sass.get()))
                implementation(devNpm("sass", libs.versions.npm.sass.get()))
                implementation(devNpm("mini-css-extract-plugin", libs.versions.npm.minicss.get()))
                implementation(devNpm("share-buttons", libs.versions.npm.sharebuttons.get()))
            }
        }
    }
}

tasks {

    val generateHtmlFiles by registering(JavaExec::class) {
        val inputDir = "pages"
        group = "run"
        mainClass = "app.MdToHtmlConverterKt"
        val jvmRuntimeClasspath by configurations
        classpath = layout.buildDirectory.files("classes/kotlin/jvm/main") + jvmRuntimeClasspath
        val outputPath = layout.buildDirectory.file("processedResources/js/main").get().asFile.absolutePath
        environment = mapOf(
            "INPUT_DIR" to inputDir,
            "OUTPUT_DIR" to outputPath
        )
        inputs.dir(inputDir)
        outputs.dir(outputPath)
        dependsOn("compileKotlinJvm")
    }

    named("jsProcessResources") {
        dependsOn(generateHtmlFiles)
    }

}
