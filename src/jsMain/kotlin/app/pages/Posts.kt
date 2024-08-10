package app.pages

import app.services.postEntries
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import react.FC
import react.dom.html.ReactHTML.div
import react.router.dom.Link

val Posts = FC {
    div {
        postEntries.sortedByDescending { it.mdFileMetadata.date }.forEach { entry ->
            div {
                div {
                    +entry.mdFileMetadata.date.format(LocalDate.Formats.ISO)

                }
                Link {
                    to = "/posts/" + entry.mdFileMetadata.id
                    +entry.mdFileMetadata.title
                }
            }
        }
    }
}
