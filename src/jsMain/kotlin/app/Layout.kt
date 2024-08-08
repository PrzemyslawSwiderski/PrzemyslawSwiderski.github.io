package app

import app.utils.withClasses
import react.FC
import react.dom.html.ReactHTML.div
import react.router.Outlet

@JsModule("./scss/styles.scss")
@JsNonModule
external val styles: dynamic

val Layout = FC {
    styles
    NavBar()
    div {
        withClasses("voronoi")
        div {
            withClasses("outlet container-fluid")
            Outlet()
        }
    }
}
