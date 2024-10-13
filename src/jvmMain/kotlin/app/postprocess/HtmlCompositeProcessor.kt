package app.postprocess

object HtmlCompositeProcessor : HtmlProcessor {

    private val bodyTagCleaner = HtmlProcessor { mdString ->
        mdString.content.removePrefix("<body>").removeSuffix("</body>")
    }

    private val processors = listOf<HtmlProcessor>(
        bodyTagCleaner,
        LinksAdjuster
    )

    override fun process(input: HtmlProcessorDto): String {
        return processors.fold(input.content) { acc, e -> e.process(input.copy(content = acc)) }
    }

}
