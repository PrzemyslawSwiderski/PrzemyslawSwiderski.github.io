package app.preprocess

import app.toc.TocProcessor

object MdCompositeProcessor : MdProcessor {

    private val normalizeSeparatorProcessor = MdProcessor { mdString -> mdString.content.replace("\r\n", "\n") }

    private val processors = listOf<MdProcessor>(
        normalizeSeparatorProcessor,
        TocProcessor
    )

    override fun process(input: MdProcessorDto): String {
        return processors.fold(input.content) { acc, e -> e.process(input.copy(content = acc)) }
    }

}
