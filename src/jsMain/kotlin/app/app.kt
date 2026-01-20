package app

import app.state.initState
import react.FC
import react.create
import react.dom.client.createRoot
import react.use.useConstant
import tanstack.react.router.RouterProvider
import web.dom.ElementId
import web.dom.document

val App = FC {
    val appRouter = useConstant(::createAppRouter)

    RouterProvider {
        router = appRouter
    }
}

suspend fun main() {
    initState()
    val container = document.getElementById(ElementId("root")) ?: error("Couldn't find root container!")
    document.body.appendChild(container)
    createRoot(container)
        .render(App.create())
}
