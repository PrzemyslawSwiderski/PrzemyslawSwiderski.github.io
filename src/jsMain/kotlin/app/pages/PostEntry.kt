package app.pages

import app.model.HtmlMetadata
import js.objects.jso
import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface PostEntryProps : Props {
    var postEntry: HtmlMetadata
}

val PostEntry = FC<PostEntryProps> { props ->
    div {
        dangerouslySetInnerHTML = jso {
            __html = props.postEntry.content
        }
    }
}
