package app.pages

import app.state.posts
import app.state.state
import app.utils.asCol
import app.utils.asRow
import app.utils.withClasses
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import react.FC
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span
import react.dom.html.ReactHTML.sub
import react.router.dom.Link
import react.useState

private const val PAGE_SIZE = 7

val Posts = FC {
    val posts = state.posts
    val (currentPage, setCurrentPage) = useState(1)

    val totalPages = (posts.size + PAGE_SIZE - 1) / PAGE_SIZE // ceiling division
    val startIndex = (currentPage - 1) * PAGE_SIZE
    val endIndex = (startIndex + PAGE_SIZE).coerceAtMost(posts.size)
    val paginatedPosts = posts.sortedByDescending { it.created }.subList(startIndex, endIndex)

    paginatedPosts.forEach { entry ->
        div {
            key = entry.id
            asRow()
            withClasses("mb-4") // optional spacing

            Link {
                asCol()
                withClasses("btn btn-outline-light btn-lg")
                to = "/posts/${entry.id}"

                span {
                    div {
                        +entry.title
                    }
                    div {
                        withClasses("mt-3")
                        sub {
                            +"${entry.created.format(LocalDate.Formats.ISO)} - ${entry.readTime}"
                        }
                    }
                }
            }
        }
    }

    div {
        asRow()
        withClasses("mt-5 justify-content-center gap-3")

        div {
            withClasses("col-auto me-auto")
            button {
                disabled = currentPage <= 1
                withClasses("btn btn-outline-light btn-lg")
                onClick = { setCurrentPage(currentPage - 1) }
                +"Previous"
            }
        }

        div {
            withClasses("col-auto")
            button {
                disabled = currentPage >= totalPages
                withClasses("btn btn-outline-light btn-lg")
                onClick = { setCurrentPage(currentPage + 1) }
                +"Next"
            }
        }
    }

    div {
        asRow()
        div {
            asCol()
            withClasses("mt-3 text-center text-muted")
            +"Page $currentPage of $totalPages (${posts.size} posts total)"
        }
    }
}