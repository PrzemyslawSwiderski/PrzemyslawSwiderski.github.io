package app.pages

import app.ShareButtons
import app.model.MdMetadata
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import react.FC
import react.Props
import react.dom.html.ReactHTML.br
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
        small { +"Created: ${props.data.created.format(LocalDate.Formats.ISO)}" }
        br {}
        if (props.data.updated != null) {
            small { +"Updated: ${props.data.updated!!.format(LocalDate.Formats.ISO)}" }
            br {}
        }
        small { +"Read time: ~${props.data.readTime}" }
        br {}
        hr { }
        InnerHtml {
            data = props.data
        }
        hr {}
        ShareButtons {}
        hr {}
        Giscus {
            id = "comments"
            host = "blog.pswidersk.com"
            repo = "PrzemyslawSwiderski/PrzemyslawSwiderski.github.io"
            repoId = "MDEwOlJlcG9zaXRvcnk1MDYxNjEyNw=="
            category = "General"
            categoryId = "DIC_kwDOAwRXP84CltPW"
            mapping = "pathname"
            reactionsEnabled = "1"
            emitMetadata = "0"
            inputPosition = "bottom"
            theme = "dark"
            lang = "en"
            loading = "lazy"
        }
    }
}
