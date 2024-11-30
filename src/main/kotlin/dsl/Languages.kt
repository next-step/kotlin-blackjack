package dsl

typealias LanguageName = String
typealias LanguageLevel = Int

data class Languages(
    val language: Map<LanguageName, LanguageLevel>,
)
