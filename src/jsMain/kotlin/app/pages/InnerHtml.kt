package app.pages

import app.model.MdMetadata
import js.import.importAsync
import js.objects.jso
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.useEffect
import react.useState

external interface InnerHtmlProps : Props {
    var data: MdMetadata
}

val InnerHtml = FC<InnerHtmlProps> { props ->
    var content: String? by useState()

    useEffect {
        importAsync<dynamic>(props.data.path + "?raw")
            .then { content = it.default.unsafeCast<String>() }
            .await()
    }

    div {
        dangerouslySetInnerHTML = jso {
            __html = content.orEmpty()
        }
    }
}
