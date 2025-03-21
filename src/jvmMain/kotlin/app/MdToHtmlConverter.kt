package app

import app.model.MdMetadata
import app.postprocess.HtmlCompositeProcessor
import app.postprocess.HtmlProcessorDto
import app.preprocess.MdCompositeProcessor
import app.preprocess.MdProcessorDto
import com.charleskorn.kaml.Yaml
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import org.intellij.markdown.flavours.gfm.GFMFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import java.io.File

private const val METADATA_SEPARATOR = "---"
private const val CONTENT_HTML_FILE = "content.html"

private val FLAVOUR = GFMFlavourDescriptor()

private val INPUT_DIR by System.getenv()
private val OUTPUT_DIR by System.getenv()

private fun File.isMarkdown(): Boolean = this.extension == "md"


private fun generateHtml(mdString: String, location: String): String {
    val processedString = MdCompositeProcessor.process(MdProcessorDto(mdString, location))
    val parsedTree = MarkdownParser(FLAVOUR).buildMarkdownTreeFromString(processedString)
    return HtmlGenerator(processedString, parsedTree, FLAVOUR)
        .generateHtml(CustomTagRenderer)
        .let { HtmlCompositeProcessor.process(HtmlProcessorDto(it, location)) }
}

private fun extractMarkdownPart(fileTxt: String) =
    fileTxt.substringAfter(METADATA_SEPARATOR).substringAfter(METADATA_SEPARATOR)

private fun saveHtml(file: File, outputDir: File, metadata: MdMetadata) {
    val fileTxt = file.readText()
    val mdString = extractMarkdownPart(fileTxt)
    val htmlString = generateHtml(mdString, metadata.location)
    val targetDir = outputDir.resolve(file.parentFile)
    targetDir.mkdirs()
    val htmlFile = targetDir.resolve(CONTENT_HTML_FILE)
    htmlFile.writeText(htmlString)
}

private fun extractMetadataPart(fileTxt: String) =
    fileTxt.substringAfter(METADATA_SEPARATOR).substringBefore(METADATA_SEPARATOR, "")


private fun generateMetadata(file: File): MdMetadata {
    val parent = file.parentFile
    val fileTxt = file.readText()
    val yamlString = extractMetadataPart(fileTxt)
    val fileMetadata = tryToParse(yamlString)
    val path = File("./").resolve(parent).resolve(CONTENT_HTML_FILE).invariantSeparatorsPath
    val location = parent.invariantSeparatorsPath.substringAfter("/")
    return fileMetadata.copy(
        id = parent.name,
        path = path,
        location = location
    )
}

private fun tryToParse(yamlString: String): MdMetadata {
    try {
        return Yaml.default.decodeFromString<MdMetadata>(yamlString)
    } catch (ex: Exception) {
        println("Exception during YAML parsing: ${ex.message}")
        return MdMetadata()
    }
}

private fun saveMetadata(metadataList: MutableList<MdMetadata>, outputDir: File) {
    val ymlTxt = Yaml.default.encodeToString(metadataList)
    val metadataFile = outputDir.resolve(INPUT_DIR).resolve("markdown-metadata.yaml")
    println("YAML Metadata file $metadataFile:")
    println(ymlTxt)
    metadataFile.writeText(ymlTxt)
}

fun main() {
    val inputDir = File(INPUT_DIR)
    val outputDir = File(OUTPUT_DIR)
    val metadataList = mutableListOf<MdMetadata>()

    inputDir.walk().forEach { file ->
        if (file.isFile && !file.isMarkdown()) {
            val targetFile = outputDir.resolve(file)
            file.copyTo(targetFile, overwrite = true)
        }
        if (file.isMarkdown()) {
            val metadata = generateMetadata(file)
            // MD -> HTML conversion
            saveHtml(file, outputDir, metadata)
            // Read and save metadata
            metadataList.add(metadata)
        }
    }

    saveMetadata(metadataList, outputDir)
}
