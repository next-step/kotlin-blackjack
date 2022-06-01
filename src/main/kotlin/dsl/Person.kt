package dsl

class Person(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: List<Language>
)

fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()

@DslMarker
annotation class PersonMaker

@PersonMaker
class PersonBuilder {
    private lateinit var name: String
    private var company: String = ""
    private var skills: Skills = Skills()
    private var languages: List<Language> = emptyList()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).languages.toList()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}
