package dsl

import dsl.vo.Language
import dsl.vo.Level

class LanguageBuilder {
    private val map = mutableMapOf<Language, Level>()

    infix fun String.level(value: Int) {
        map[Language(this)] = Level(value)
    }

    fun build(): Languages {
        return Languages(map)
    }
}

fun introduce(block: ResumeBuilder.() -> Unit): Resume {
    return ResumeBuilder().apply(block).build()
}
