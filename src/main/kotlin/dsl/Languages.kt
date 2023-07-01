package dsl

@JvmInline
value class Languages(val value: List<Language>) {

    operator fun get(index: Int): Language {
        return value[index]
    }

    class Builder {
        private val languages = mutableListOf<Language>()

        infix fun String.level(other: Int) {
            languages.add(Language(name = this, level = other))
        }

        fun build(): Languages {
            return Languages(languages.toList())
        }
    }
}

fun Languages?.orEmpty() = this ?: Languages(emptyList())

data class Language(val name: String, val level: Int)
