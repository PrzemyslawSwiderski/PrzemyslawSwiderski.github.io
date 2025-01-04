@file:JsModule("@giscus/react")
@file:JsNonModule

package app.pages

import react.FC
import react.Props

@JsName("default")
external val Giscus: FC<GiscusProps>

external interface GiscusProps : Props {
    var id: String
    var host: String
    var repo: String
    var repoId: String
    var category: String
    var categoryId: String
    var mapping: String
    var theme: String
    var reactionsEnabled: String
    var emitMetadata: String
    var inputPosition: String
    var lang: String
    var loading: String
}
