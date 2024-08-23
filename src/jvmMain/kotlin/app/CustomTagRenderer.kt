package app

import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.html.AttributesCustomizer
import org.intellij.markdown.html.HtmlGenerator.DefaultTagRenderer

private class CustomAttributesCustomizer : AttributesCustomizer {
    override fun invoke(
        node: ASTNode,
        tagName: CharSequence,
        attributes: Iterable<CharSequence?>
    ): Iterable<CharSequence?> {
        when (tagName) {
            "a" -> {
                val anchorHref = attributes.firstOrNull { it?.startsWith("href") == true && it.contains("#") }
                when {
                    anchorHref != null -> {
                        val id = anchorHref.toString().substringAfterLast("#")
                        return listOf(
                            anchorHref,
                            "class=\"anchor-link\"",
                            "id=\"$id"
                        )
                    }
                }
            }
        }
        return attributes
    }

}

class CustomTagRenderer : DefaultTagRenderer(CustomAttributesCustomizer(), false)
