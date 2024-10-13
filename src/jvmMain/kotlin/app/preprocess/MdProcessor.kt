package app.preprocess


fun interface MdProcessor {

    fun process(input: MdProcessorDto): String

}
