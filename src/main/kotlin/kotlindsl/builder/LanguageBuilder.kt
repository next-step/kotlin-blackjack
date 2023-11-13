package kotlindsl.builder

import kotlindsl.model.Language

class LanguageBuilder {
    private val languageList: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languageList.add(Language(this, level))
    }

    fun build(): List<Language> = this.languageList
}
