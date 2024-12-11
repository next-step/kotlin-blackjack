package dsl

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills,
    var languages: Map<String, Int>,
)

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: SkillsBuilder = SkillsBuilder()
    private val languages: MutableMap<String, Int> = mutableMapOf()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills.apply(block)
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        LanguagesBuilder(languages).apply(block)
    }

    fun build(): Person {
        return Person(name, company, skills.build(), languages)
    }
}

class LanguagesBuilder(private val languages: MutableMap<String, Int>) {
    infix fun String.level(value: Int) {
        languages[this] = value
    }
}
