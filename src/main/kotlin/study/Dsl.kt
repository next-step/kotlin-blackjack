package study

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(
    val name: String,
    val company: String,
    val skills: List<Skill>,
    val languages: List<Language>
)

class PersonBuilder {

    private var name: String = ""
    private var company: String = ""
    private var skills = mutableListOf<Skill>()
    private var languages = mutableListOf<Language>()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        SkillsBuilder().apply(block).build().also { skills.addAll(it) }
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        LanguagesBuilder().apply(block).build().also { languages.addAll(it) }
    }

    fun build(): Person = Person(
        name = name,
        company = company,
        skills = skills,
        languages = languages
    )
}

interface Skill {
    val description: String
}

data class Soft(override val description: String) : Skill

data class Hard(override val description: String) : Skill

class SkillsBuilder {

    private val skills = mutableListOf<Skill>()

    fun soft(description: String) {
        skills.add(Soft(description))
    }

    fun hard(description: String) {
        skills.add(Hard(description))
    }

    fun build(): List<Skill> {
        return skills
    }
}

data class Language(val language: String, val level: Int)

class LanguagesBuilder {

    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): List<Language> {
        return languages
    }
}
