package dslstudy

data class Language(
    val name: String,
    val level: Int,
)

class LanguagesBuilder(
    private val languages: MutableList<Language> = mutableListOf(),
) {
    infix fun String.level(level: Int) {
        val language = Language(this, level)
        languages.add(language)
    }

    fun build(): List<Language> = languages.toList()
}

class A {
    val a = 10
}

class B {
    val a = 20
    val b = 30

    fun A.aTest() = a + b + this@B.a
}
