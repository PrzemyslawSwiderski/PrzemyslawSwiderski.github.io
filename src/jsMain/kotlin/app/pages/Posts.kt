package app.pages

import app.state.posts
import app.state.state
import app.utils.asCol
import app.utils.asRow
import app.utils.centered
import app.utils.withClasses
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import react.FC
import react.dom.html.ReactHTML.b
import react.dom.html.ReactHTML.div
import react.router.dom.Link

val Posts = FC {
    div {
        asRow()
        div {
            asCol()
            centered()
            b {
                +"Created:"
            }
        }
        div {
            asCol()
            centered()
            b {
                +"Post:"
            }
        }
    }
    state.posts.sortedByDescending { it.created }.forEach { entry ->
        div {
            asRow()
            div {
                asCol()
                centered()
                +entry.created.format(LocalDate.Formats.ISO)
            }
            Link {
                asCol()
                withClasses("btn btn-outline-light btn-lg")
                to = "/posts/" + entry.id
                +entry.title
            }
        }
    }
}
