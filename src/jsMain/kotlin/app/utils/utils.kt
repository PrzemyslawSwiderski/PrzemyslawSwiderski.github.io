package app.utils

import react.PropsWithClassName
import web.cssom.ClassName

fun PropsWithClassName.withClasses(value: String) {
    if (this.className == null) {
        this.className = ClassName(value)
    } else {
        this.className = ClassName(this.className.unsafeCast<String>() + " " + value)
    }
    console.log(this)
}

fun PropsWithClassName.asRow() {
    withClasses("row")
}

fun PropsWithClassName.asCol() {
    withClasses("col")
}

fun PropsWithClassName.asNavItem() {
    this.className = ClassName("nav-item")
}
