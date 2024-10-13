package app.postprocess

object LinksAdjuster : HtmlProcessor {

    private const val SRC_ATTR_REGEX = """src=['"](\S*)['"]"""
    private const val HREF_ATTR_REGEX = """href=['"](\S*)['"]"""

    override fun process(input: HtmlProcessorDto): String {
        val (content, location) = input
        return content
            .replace(Regex(SRC_ATTR_REGEX)) { resolveSource(it, location, "src") }
            .replace(Regex(HREF_ATTR_REGEX)) { resolveSource(it, location, "href") }
    }

    private fun resolveSource(result: MatchResult, location: String, attr: String): CharSequence {
        val path = result.groups[1]?.value ?: ""
        return if (path.startsWith("http") || path.startsWith("/"))
            result.value
        else
            "$attr=\"/pages/$location/$path\""
    }

}
