package app

import app.utils.asCol
import app.utils.asRow
import app.utils.withClasses
import react.FC
import react.dom.html.ReactHTML.div
import react.use.useConstant
import tanstack.react.router.RouterProvider


val Layout = FC {
    val appRouter = useConstant(::createAppRouter)

    NavBar()
    div {
        withClasses("container blurred-bg")
        div {
            asRow()
            div {
                asCol()
                RouterProvider {
                    router = appRouter
                }
            }
        }
    }
}
