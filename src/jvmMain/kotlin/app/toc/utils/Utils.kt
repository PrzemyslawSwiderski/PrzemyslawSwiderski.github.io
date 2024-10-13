package app.toc.utils

object Utils {
    private const val SPACE = ' '
    private const val DASH = '-'

    fun normalize(taintedURL: String) = taintedURL
        .trim()
        .replace(SPACE, DASH)
        .filter { it.isLetterOrDigit() || it == DASH }
        .lowercase()
}
