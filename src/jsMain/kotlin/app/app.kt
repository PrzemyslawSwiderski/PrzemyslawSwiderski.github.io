package app

import app.state.initState
import react.Fragment
import react.create
import react.dom.client.createRoot
import react.router.RouterProvider
import web.dom.ElementId
import web.dom.document

suspend fun main() {
    initState()
    val container = document.getElementById(ElementId("root")) ?: error("Couldn't find root container!")
    createRoot(container).render(
        Fragment.create {
            RouterProvider {
                router = Router
            }
        }
    )
}
