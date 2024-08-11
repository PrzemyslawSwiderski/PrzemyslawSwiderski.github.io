package app.pages

import app.model.HtmlData
import js.objects.jso
import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface InnerHtmlProps : Props {
    var data: HtmlData
}

val InnerHtml = FC<InnerHtmlProps> { props ->
    div {
        dangerouslySetInnerHTML = jso {
            __html = props.data.content
        }
    }
}
