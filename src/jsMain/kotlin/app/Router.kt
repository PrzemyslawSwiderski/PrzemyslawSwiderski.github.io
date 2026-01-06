package app

import app.pages.About
import app.pages.Posts
import app.pages.SinglePost
import app.state.posts
import app.state.state
import react.FC
import react.Props
import tanstack.react.router.*
import tanstack.router.core.RoutePath

//
//val Router = createBrowserRouter(
//    arrayOf(
//        RouteObject(
//            path = "/",
//            Component = Layout,
//            children = arrayOf(
//                RouteObject(
//                    index = true,
//                    Component = About,
//                ),
//                RouteObject(
//                    path = "*",
//                    Component = About,
//                ),
//                RouteObject(
//                    path = "posts",
//                    Component = Posts,
//                ),
//            ) + state.posts.map { entry ->
//                RouteObject(
//                    path = "posts/" + entry.id,
//                    element = SinglePost.create {
//                        data = entry
//                    },
//                )
//            }.toTypedArray()
//        )
//    )
//)

fun createAppRouter(): Router {
    val rootRoute = createRootRoute(
//        options = RootRouteOptions(component = Layout)
    )

    val indexRoute = createRoute(
        options = RouteOptions(
            getParentRoute = { rootRoute },
            path = RoutePath("/"),
            component = About,
        ),
    )

    val aboutRoute = createRoute(
        options = RouteOptions(
            getParentRoute = { rootRoute },
            path = RoutePath("*"),
            component = About,
        ),
    )

    val postsRoute = createRoute(
        options = RouteOptions(
            getParentRoute = { rootRoute },
            path = RoutePath("posts"),
            component = Posts,
        ),
    )

    postsRoute.addChildren(
        state.posts.map { entry ->
            val postComponent = FC<Props> {
                SinglePost {
                    data = entry
                }
            }
            createRoute(
                options = RouteOptions(
                    getParentRoute = { postsRoute },
                    path = RoutePath(entry.id),
                    component = postComponent
                ),
            )
        }.toTypedArray()
    )

//    rootRoute.addChildren(
//        arrayOf(
//            indexRoute,
//            aboutRoute,
//            postsRoute
//        ),
//    )

    return createRouter(
        options = RouterOptions(
            routeTree = rootRoute,
        )
    )
}