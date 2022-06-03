package dsl.domain

/**
 * Created by Jaesungchi on 2022.06.03..
 */
class LanguageBuilder {
    private var languages = Languages()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages {
        return languages
    }
}
