package app

import org.intellij.markdown.html.AttributesCustomizer
import org.intellij.markdown.html.HtmlGenerator.DefaultTagRenderer


private val attributesCustomizer: AttributesCustomizer = { _, tagName, attributes ->
    when (tagName) {
        "img" -> attributes + """class="markdown-img""""
        else -> attributes
    }
}

object CustomTagRenderer : DefaultTagRenderer(attributesCustomizer, false)
