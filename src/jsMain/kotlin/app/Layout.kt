package app

import app.utils.asCol
import app.utils.asRow
import app.utils.withClasses
import react.FC
import react.dom.html.ReactHTML.div
import react.router.Outlet


val Layout = FC {
    NavBar()
    div {
        withClasses("container")
        div {
            asRow()
            div {
                asCol()
                withClasses("offset-1")
                Outlet()
            }
        }
    }
}
