package app.toc.model

class ModifyModel(
    private val currentDeepLevel: Int,
    private val headerName: String,
    private val headerPath: String
) {

    fun create(): String {
        var affixs = ""
        if (currentDeepLevel > 1) {
            (0 until currentDeepLevel - 1)
                .forEach { affixs += AFFIX }
        }
        return "$affixs- [$headerName]($headerPath)"
    }

    companion object {
        private const val AFFIX = "  "
    }
}
