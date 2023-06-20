package next.step.dsl

fun languages(block: LanguagesBuilder.() -> Unit): Languages = LanguagesBuilder().apply(block).build()

@JvmInline
value class Languages(private val languagesMap: Map<String, Int> = emptyMap()) {
    fun get(language: String): Int? {
        return languagesMap[language]
    }
}

class LanguagesBuilder() {
    var languagesMap: MutableMap<String, Int> = mutableMapOf()
    infix fun String.level(level: Int) {
        languagesMap[this] = level
    }

    fun build(): Languages = Languages(languagesMap)
}
