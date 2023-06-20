package dsl.builder

import dsl.domain.Language
import dsl.domain.Languages

class LanguagesBuilder(block: LanguagesBuilder.() -> Unit) {
    private val languageList: MutableList<Language> = mutableListOf()

    init {
        apply(block)
    }

    infix fun String.level(level: Int) {
        languageList.add(Language(this, level))
    }

    fun build(): Languages {
        return Languages(languageList)
    }
}
