package app

import app.utils.asNavItem
import app.utils.withClasses
import react.FC
import react.dom.html.ReactHTML.img
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
                    withClasses("navbar-brand nav-link")
                    to = "/"
                    img {
                        withClasses("rounded")
                        src = "img/home-logo.png"
                        alt = "Home"
                        width = 40.0
                        height = 50.0
                    }
                }
            }
            li {
                asNavItem()
                NavLink {
                    withClasses("nav-link")
                    to = "/about"
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
