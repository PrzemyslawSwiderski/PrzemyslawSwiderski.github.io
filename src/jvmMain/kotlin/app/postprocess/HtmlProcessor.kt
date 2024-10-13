package app.postprocess

fun interface HtmlProcessor {

    fun process(input: HtmlProcessorDto): String

}
