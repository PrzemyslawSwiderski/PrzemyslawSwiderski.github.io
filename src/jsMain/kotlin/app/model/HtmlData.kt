package app.model

import kotlinx.serialization.Serializable

@Serializable
data class HtmlData(
    val mdFileMetadata: MdFileMetadata,
    val content: String
)
