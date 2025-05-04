package dsl

@PersonDsl
data class Languages(
    val values: MutableList<Language> = mutableListOf(),
) {
    infix fun String.level(level: Int) {
        values.add(Language(this, level))
    }
}

data class Language(val name: String, val level: Int)
