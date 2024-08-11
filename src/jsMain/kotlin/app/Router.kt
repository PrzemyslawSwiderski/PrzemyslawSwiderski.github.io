package app

import app.pages.About
import app.pages.InnerHtml
import app.pages.Posts
import app.state.posts
import app.state.state
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
                    Component = About,
                ),
                RouteObject(
                    path = "*",
                    Component = About,
                ),
                RouteObject(
                    path = "posts",
                    Component = Posts,
                ),
            ) + state.posts.map { entry ->
                RouteObject(
                    path = "posts/" + entry.id,
                    element = InnerHtml.create {
                        data = entry
                    },
                )
            }.toTypedArray()
        )
    )
)
