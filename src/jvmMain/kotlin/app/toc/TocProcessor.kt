package app.toc

import app.preprocess.MdProcessor
import app.preprocess.MdProcessorDto
import app.toc.model.ModifyModel
import app.toc.utils.Utils
import java.util.ArrayList

/**
 * Ported to kotlin form [MarkdownTocGenerator](https://github.com/YuraAAA/Markdown-Toc-Generator/)
 */
object TocProcessor : MdProcessor {

    override fun process(input: MdProcessorDto): String {
        val (content, location) = input
        val rootModels: MutableList<ModifyModel> = ArrayList<ModifyModel>()
        val outputLines: MutableList<String> = ArrayList<String>()
        var inCodeBlock = false

        for (line in content.lines()) {
            var outputLine = line
            when {
                outputLine.trim().startsWith(CODEBLOCK_STRING) -> {
                    inCodeBlock = !inCodeBlock
                }

                !inCodeBlock && outputLine.trim().startsWith(DEFAULT_HEADER_CHAR) -> {
                    outputLine = addAnchorLink(outputLine, location, rootModels)
                }
            }
            outputLines.add(outputLine)
        }

        val tocSection = rootModels.map { it.create() }.joinToString(separator = LINE_SEP)

        return outputLines.joinToString(separator = LINE_SEP).replace(TOC_PLACEHOLDER, tocSection)
    }

    private fun addAnchorLink(
        outputLine: String,
        location: String,
        rootModels: MutableList<ModifyModel>
    ): String {
        val count = getNestLevel(outputLine)

        val headerName = outputLine.substring(count)
        val headerId = Utils.normalize(headerName)
        val headerPath = "/$location#$headerId"
        rootModels.add(ModifyModel(count, headerName, headerPath))
        return "$outputLine <a id=\"$headerId\" href=\"$headerPath\" class=\"anchor-link\">\uD83D\uDD17</a>"
    }


    private fun getNestLevel(string: String): Int = string.takeWhile { it == DEFAULT_HEADER_CHAR }.count()

    const val TOC_PLACEHOLDER = "{toc.placeholder}"
    const val LINE_SEP = "\n"
    const val CODEBLOCK_STRING: String = "```"
    const val DEFAULT_HEADER_CHAR: Char = '#'

}
