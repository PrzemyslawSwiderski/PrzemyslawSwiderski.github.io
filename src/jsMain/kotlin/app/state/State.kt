package app.state

import app.model.MdMetadata
import js.import.importAsync
import js.promise.toResult
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromDynamic

data class State(val metadata: MutableList<MdMetadata>) {
    fun filterByPath(path: String): List<MdMetadata> {
        return metadata.filter { it.path.startsWith(path) }
    }
}

val state = State(mutableListOf())

val State.about: MdMetadata
    get() {
        return filterByPath("./pages/about/").first()
    }

val State.posts: List<MdMetadata>
    get() = filterByPath("./pages/posts/")


@OptIn(ExperimentalSerializationApi::class)
suspend fun initState() = importAsync<dynamic>("./pages/markdown-metadata.yaml")
    .then { Json.decodeFromDynamic<List<MdMetadata>>(it.default) }
    .toResult()
    .onSuccess { state.metadata.addAll(it) }
