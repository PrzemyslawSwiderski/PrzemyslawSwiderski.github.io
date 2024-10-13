package app.toc.utils

import java.util.Locale

object Utils {
    private const val SPACES = " "
    private const val CODES = "%([abcdef]|\\d){2,2}"
    private const val SPECIAL_CHARS = "[\\/?!:\\[\\]`.,()*\"';{}+=<>~\\$|#]"
    private const val DASH = "-"
    private const val EMPTY = ""

    fun normalize(taintedURL: String?): String {
        if (taintedURL == null) return ""
        return taintedURL
            .trim { it <= ' ' }
            .replace(SPACES.toRegex(), DASH)
            .replace(CODES.toRegex(), EMPTY)
            .replace(SPECIAL_CHARS.toRegex(), EMPTY)
            .lowercase(Locale.getDefault())
    }
}
