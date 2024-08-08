package app.pages

import app.utils.asRow
import app.utils.withClasses
import react.FC
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import web.fs.FileSystemHandleKind

val Home = FC {
    div {
        withClasses("row justify-content-md-center")
        img {
            withClasses("rounded")
            src = "img/github-logo.svg"
            alt = "Github"
            width = 100.0
            height = 100.0
        }
    }
    div {
        asRow()
        div {
            FileSystemHandleKind.file
            +"""Home Page
                """
        }
    }
}
