package app.utils

import react.PropsWithClassName
import web.cssom.ClassName

fun PropsWithClassName.withClasses(value: String) {
    if (this.className == null) {
        this.className = ClassName(value)
    } else {
        this.className = ClassName(this.className.unsafeCast<String>() + " " + value)
    }
}

fun PropsWithClassName.asRow() {
    withClasses("row")
}

fun PropsWithClassName.asCol() {
    withClasses("col my-3 mx-2")
}

fun PropsWithClassName.centered() {
    withClasses("align-items-center justify-content-center text-center")
}

fun PropsWithClassName.asNavItem() {
    this.className = ClassName("nav-item")
}
