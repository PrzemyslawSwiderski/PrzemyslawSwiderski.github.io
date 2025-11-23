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
import react.dom.html.ReactHTML.span
import react.dom.html.ReactHTML.sub
import react.router.dom.Link

val Posts = FC {
    state.posts.sortedByDescending { it.created }.forEach { entry ->
        div {
            asRow()
            Link {
                asCol()
                withClasses("btn btn-outline-light btn-lg")
                to = "/posts/" + entry.id
                span {
                    div {
                        +entry.title
                    }
                    div {
                        withClasses("mt-3")
                        sub {
                            +entry.created.format(LocalDate.Formats.ISO)
                        }
                        sub {
                            +" - ${entry.readTime}"
                        }
                    }
                }
            }
        }
    }
}
