package app.pages

import app.services.postEntries
import app.utils.withClasses
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import react.FC
import react.dom.html.ReactHTML.div
import react.router.dom.Link

val Posts = FC {
    div {
        withClasses("container text-center")
        postEntries.sortedByDescending { it.mdFileMetadata.date }.forEach { entry ->
            div {
                withClasses("row justify-content-lg-center")
                div {
                    withClasses("col-lg-auto")
                    +entry.mdFileMetadata.date.format(LocalDate.Formats.ISO)

                }
                Link {
                    withClasses("col-lg-auto")
                    to = "/posts/" + entry.mdFileMetadata.id
                    +entry.mdFileMetadata.title
                }
            }
        }
    }
}
