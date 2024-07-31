package app.services

import app.model.HtmlMetadata
import app.model.MdFileMetadata
import js.import.importAsync
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromDynamic

val postEntries = mutableListOf<HtmlMetadata>()

@OptIn(ExperimentalSerializationApi::class)
suspend fun initPosts() {
    MainScope().launch {
        val metadataDyn = importAsync<dynamic>("./posts/markdown-metadata.yaml").await().default
        val metadata = Json.decodeFromDynamic<List<MdFileMetadata>>(metadataDyn)

        metadata.forEach {
            val postContent = importAsync<dynamic>(it.path + "?raw").await().default
            val content = Json.decodeFromDynamic<String>(postContent)
            postEntries.add(HtmlMetadata(it, content))
        }
    }.join()
}