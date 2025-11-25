package app.pages

import app.model.MdMetadata
import app.utils.withClasses
import js.import.importAsync
import js.promise.await
import kotlinx.browser.document
import kotlinx.browser.window
import react.FC
import react.Props
import react.dom.DangerouslySetInnerHTML
import react.dom.html.ReactHTML.div
import react.useEffect
import react.useState
import web.html.HtmlSource

external interface InnerHtmlProps : Props {
    var data: MdMetadata
}

val InnerHtml = FC<InnerHtmlProps> { props ->
    var content: String? by useState()

    useEffect {
        importAsync<dynamic>(props.data.path + "?raw")
            .then { content = it.default.unsafeCast<String>() }
            .await()
        importAsync<HighlightJSModule>("highlight.js")
            .then {
                val copyPlugin = CopyButtonPlugin()
                copyPlugin.autohide = true
                it.default.addPlugin(copyPlugin)
                it.default.highlightAll()
            }
            .await()
        scrollToAnchor()
    }

    div {
        withClasses("mb-5")

        dangerouslySetInnerHTML = DangerouslySetInnerHTML(
            HtmlSource(content.orEmpty())
        )
    }
}

private fun scrollToAnchor() {
    val anchor = window.location.hash.substringAfter("#")
    document.getElementById(anchor)?.scrollIntoView()
}

external class HighlightJSModule {
    var default: HighlightJS
}

external class HighlightJS {
    fun highlightAll()
    fun addPlugin(plugin: dynamic)
}

@JsModule("highlightjs-copy")
@JsNonModule
external class CopyButtonPlugin() {
    var autohide: Boolean
}
