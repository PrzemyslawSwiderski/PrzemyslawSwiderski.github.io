package app.pages

import app.model.MdMetadata
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.hr
import react.dom.html.ReactHTML.small


external interface SinglePostProps : Props {
    var data: MdMetadata
}

val SinglePost = FC<SinglePostProps> { props ->
    div {
        h1 {
            +props.data.title
        }
        small { +props.data.created.toString() }
        hr { }
        InnerHtml {
            data = props.data
        }
    }
}
