import model.Languages
import model.Person
import model.Skills

@DslMarker
annotation class PersonDsl

@PersonDsl
class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: Languages? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills =
            SkillsBuilder()
                .apply(block)
                .build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages =
            LanguagesBuilder()
                .apply(block)
                .build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person =
    PersonBuilder()
        .apply(block)
        .build()
