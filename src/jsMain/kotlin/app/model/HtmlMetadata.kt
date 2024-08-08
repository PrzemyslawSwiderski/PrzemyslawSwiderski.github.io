package app.model

import kotlinx.serialization.Serializable

@Serializable
data class HtmlMetadata(
    val mdFileMetadata: MdFileMetadata,
    val content: String
)
