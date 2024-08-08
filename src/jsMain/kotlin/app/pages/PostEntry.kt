package app.pages

import app.model.HtmlMetadata
import app.utils.withClasses
import js.objects.jso
import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface PostEntryProps : Props {
    var postEntry: HtmlMetadata
}

val PostEntry = FC<PostEntryProps> { props ->
    div {
        withClasses("container")
        dangerouslySetInnerHTML = jso {
            __html = props.postEntry.content
        }
    }
}
