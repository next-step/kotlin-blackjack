package study

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String = ""
    private val skills = mutableListOf<Skill>()
    private val languages = mutableListOf<String>()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(skill: Skill) {
        skills.add(skill)
    }

    fun languages(language: Language) {
        languages.add("\"${language.name}\" level ${language.level}")
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

data class Person(var name: String, var company: String, var skills: List<Skill>, var languages: List<String>)
data class Skill(var name: String, var value: String)
data class Language(var name: String, var level: Int)
