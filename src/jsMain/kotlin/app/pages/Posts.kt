package app.pages

import app.state.posts
import app.state.state
import app.utils.asCol
import app.utils.asRow
import app.utils.withClasses
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import react.FC
import react.dom.aria.AriaRole
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div

val Posts = FC {
    state.posts.sortedByDescending { it.mdFileMetadata.date }.forEach { entry ->
        div {
            asRow()
            div {
                asCol()
                +entry.mdFileMetadata.date.format(LocalDate.Formats.ISO)
            }
            a {
                asCol()
                withClasses("btn btn-outline-light btn-lg px-4")
                href = "/posts/" + entry.mdFileMetadata.id
                role = AriaRole.button
                +entry.mdFileMetadata.title
            }
        }
    }
}
