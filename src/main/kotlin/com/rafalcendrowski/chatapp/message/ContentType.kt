package com.rafalcendrowski.chatapp.message

import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser

enum class ContentType {
    PLAIN,
    MARKDOWN;

    fun render(content: String): String {
        return when(this) {
            PLAIN -> content
            MARKDOWN -> {
                val flavour = CommonMarkFlavourDescriptor()
                HtmlGenerator(
                        content,
                        MarkdownParser(flavour).buildMarkdownTreeFromString(content),
                        flavour).generateHtml()
            }
        }
    }
}