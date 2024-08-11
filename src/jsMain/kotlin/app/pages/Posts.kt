package app.pages

import app.state.posts
import app.state.state
import app.utils.asCol
import app.utils.asRow
import app.utils.withClasses
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import react.FC
import react.dom.html.ReactHTML.div
import react.router.dom.Link

val Posts = FC {
    state.posts.sortedByDescending { it.date }.forEach { entry ->
        div {
            asRow()
            div {
                asCol()
                +entry.date.format(LocalDate.Formats.ISO)
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
