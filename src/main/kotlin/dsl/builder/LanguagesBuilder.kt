package dsl.builder

import dsl.domain.Language
import dsl.domain.Languages

class LanguagesBuilder {
    private val languageList: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languageList.add(Language(this, level))
    }

    fun build(): Languages {
        return Languages(languageList)
    }
}
