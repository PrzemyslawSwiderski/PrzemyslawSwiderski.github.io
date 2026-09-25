package app.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
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
    withClasses("row mb-3")
}

fun PropsWithClassName.asCol() {
    withClasses("col my-3 mx-2")
}

fun PropsWithClassName.asNavItem() {
    this.className = ClassName("nav-item")
}

fun LocalDate.asIso(): String {
    return this.format(LocalDate.Formats.ISO)
}