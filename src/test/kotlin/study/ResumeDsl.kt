package study

data class Person(val name: String, val company: String?, val skills: List<Skill>, val languages: List<Language>)

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

interface Skill {
    val description: String
}
data class SoftSkill(override val description: String): Skill
data class HardSkill(override val description: String): Skill

data class Language(val language: String, val level: Int)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val skills: MutableList<Skill> = mutableListOf()
    private val languages: MutableList<Language> = mutableListOf()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun soft(description: String) {
        skills.add(SoftSkill(description))
    }

    fun hard(description: String) {
        skills.add(HardSkill(description))
    }

    fun skills(block: PersonBuilder.() -> Unit) {
        block()
    }

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun languages(block: PersonBuilder.() -> Unit) {
        block()
    }

    fun build(): Person {
        return Person(name, company, skills.toList(), languages.toList())
    }
}