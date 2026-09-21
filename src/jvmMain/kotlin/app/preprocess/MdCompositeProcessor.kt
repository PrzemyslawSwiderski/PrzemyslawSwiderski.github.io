package app.preprocess

import app.toc.TocProcessor

object MdCompositeProcessor : MdProcessor {

    private val normalizeSeparatorProcessor = MdProcessor { mdObject ->
        (mdObject.content as String)
            .replace("\r\n", "\n")
    }

    private val processors = listOf(
        normalizeSeparatorProcessor,
        TocProcessor
    )

    override fun process(input: MdProcessorDto): CharSequence {
        return processors.fold(input.content) { acc, e -> e.process(input.copy(content = acc)) }
    }

}
