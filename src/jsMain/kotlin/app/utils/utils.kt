package app.utils

import react.PropsWithClassName
import web.cssom.ClassName

fun PropsWithClassName.withClasses(value: String) {
    this.className = ClassName(value)
}

fun PropsWithClassName.asRow() {
    this.className = ClassName("row")
}

fun PropsWithClassName.asNavItem() {
    this.className = ClassName("nav-item")
}
