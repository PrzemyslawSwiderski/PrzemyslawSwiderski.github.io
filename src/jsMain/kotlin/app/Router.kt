package app

import app.pages.About
import app.pages.Home
import app.pages.PostEntry
import app.pages.Posts
import app.services.postEntries
import react.create
import react.router.RouteObject
import react.router.dom.createBrowserRouter

val Router = createBrowserRouter(
    arrayOf(
        RouteObject(
            path = "/",
            Component = Layout,
            children = arrayOf(
                RouteObject(
                    index = true,
                    Component = Home,
                ),
                RouteObject(
                    path = "about",
                    Component = About,
                ),
                RouteObject(
                    path = "posts",
//                    Component = Posts,
                    children = postEntries.map { entry ->
                        RouteObject(
                            path = entry.mdFileMetadata.id,
                            element = PostEntry.create {
                                postEntry = entry
                            },
                        )
                    }.toTypedArray() + RouteObject(index = true, Component = Posts)
                ),
                RouteObject(
                    path = "*",
                    Component = Home,
                )
            )
        )
    )
)