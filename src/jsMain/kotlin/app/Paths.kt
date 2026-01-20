package app

import tanstack.router.core.RoutePath


val INDEX_PATH: RoutePath = RoutePath("/")
val ALL_OTHERS_PATH: RoutePath = RoutePath("*")
val POSTS_PATH: RoutePath = RoutePath("/posts")
val POST_PATH: (String) -> RoutePath = { RoutePath("/posts/${it}") }