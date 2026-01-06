package app

import app.state.initState
import react.FC
import react.create
import react.dom.client.createRoot
import react.use.useConstant
import tanstack.react.router.RouterProvider
import web.dom.document
import web.html.HtmlTagName.div

val App = FC {
    val appRouter = useConstant(::createAppRouter)

    RouterProvider {
        router = appRouter
    }
}

suspend fun main() {
    initState()
//    val container = document.getElementById(ElementId("root")) ?: error("Couldn't find root container!")

    val container = document.createElement(div)
    document.body.appendChild(container)
    createRoot(container)
        .render(App.create())
}
