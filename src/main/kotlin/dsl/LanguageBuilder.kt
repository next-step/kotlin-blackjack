package dsl

class LanguageBuilder {
    private val map = mutableMapOf<String, Int>()

    infix fun String.level(value: Int) {
        map[this] = value
    }

    fun build(): Languages {
        return Languages(map)
    }
}

fun introduce(block: ResumeBuilder.() -> Unit): Resume {
    return ResumeBuilder().apply(block).build()
}
