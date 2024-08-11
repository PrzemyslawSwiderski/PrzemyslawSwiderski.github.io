package app

import app.utils.asNavItem
import app.utils.withClasses
import react.FC
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.nav
import react.dom.html.ReactHTML.ul
import react.router.dom.NavLink

val NavBar = FC {
    nav {
        withClasses("navbar navbar-expand-sm")
        ul {
            withClasses("navbar-nav")
            li {
                asNavItem()
                NavLink {
                    withClasses("nav-link")
                    to = "/"
                    +"About"
                }
            }
            li {
                asNavItem()
                NavLink {
                    withClasses("nav-link")
                    to = "/posts"
                    +"Posts"
                }
            }
        }

    }
}
