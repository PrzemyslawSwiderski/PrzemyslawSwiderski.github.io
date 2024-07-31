package app

import app.services.initPosts
import react.Fragment
import react.create
import react.dom.client.createRoot
import react.router.RouterProvider
import web.dom.document

suspend fun main() {
    initPosts()
    val container = document.getElementById("root") ?: error("Couldn't find root container!")
    createRoot(container).render(
        Fragment.create {
            RouterProvider {
                router = Router
            }
        }
    )
}