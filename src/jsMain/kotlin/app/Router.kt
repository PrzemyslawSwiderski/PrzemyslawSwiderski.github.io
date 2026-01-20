package app

import app.pages.About
import app.pages.Posts
import app.pages.SinglePost
import app.state.posts
import app.state.state
import react.FC
import react.Props
import tanstack.react.router.*

fun createAppRouter(): Router {
    val rootRoute = createRootRoute(
        options = RootRouteOptions(component = Layout)
    )

    val indexRoute = createRoute(
        options = RouteOptions(
            getParentRoute = { rootRoute },
            path = INDEX_PATH,
            component = About,
        ),
    )

    val aboutRoute = createRoute(
        options = RouteOptions(
            getParentRoute = { rootRoute },
            path = ALL_OTHERS_PATH,
            component = About,
        ),
    )

    val postsRoute = createRoute(
        options = RouteOptions(
            getParentRoute = { rootRoute },
            path = POSTS_PATH,
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
                    getParentRoute = { rootRoute },
                    path = POST_PATH(entry.id),
                    component = postComponent
                ),
            )
        }.toTypedArray()
    )

    rootRoute.addChildren(
        arrayOf(
            indexRoute,
            aboutRoute,
            postsRoute
        ),
    )

    return createRouter(
        options = RouterOptions(
            routeTree = rootRoute,
        )
    )
}