package app

import app.utils.asNavItem
import app.utils.withClasses
import react.FC
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.nav
import react.dom.html.ReactHTML.ul
import tanstack.react.router.Link
import tanstack.router.core.RoutePath

val NavBar = FC {
    nav {
        withClasses("navbar navbar-expand-sm blurred-bg mx-2 mt-0 mb-2")
        ul {
            withClasses("navbar-nav")
            li {
                asNavItem()
                Link {
                    withClasses("nav-link")
                    to = RoutePath("/")
                    +"About"
                }
            }
            li {
                asNavItem()
                Link {
                    withClasses("nav-link")
                    to = RoutePath("/posts")
                    +"Posts"
                }
            }
        }

    }
}
