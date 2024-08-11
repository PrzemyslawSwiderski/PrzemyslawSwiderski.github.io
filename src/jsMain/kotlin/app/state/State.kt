package app.state

import app.model.HtmlData
import app.model.MdFileMetadata
import js.import.importAsync
import js.promise.toResult
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromDynamic

data class State(val metadata: MutableList<HtmlData>) {
    fun filterByPath(path: String): List<HtmlData> {
        return metadata.filter { it.mdFileMetadata.path.startsWith(path) }
    }
}

val state = State(mutableListOf())

val State.about: HtmlData
    get() {
        return filterByPath("./pages/about/").first()
    }

val State.posts: List<HtmlData>
    get() = filterByPath("./pages/posts/")


@OptIn(ExperimentalSerializationApi::class)
suspend fun initState() = importAsync<dynamic>("./pages/markdown-metadata.yaml")
    .then { Json.decodeFromDynamic<List<MdFileMetadata>>(it.default) }
    .toResult()
    .map { metadataList ->
        metadataList.map { metadata ->
            importAsync<dynamic>(metadata.path + "?raw")
                .then { it.default }
                .then { HtmlData(metadata, it) }
                .then { state.metadata.add(it) }
                .await()
        }
    }
