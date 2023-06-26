package introduce

data class Person(val name: String, val company: String, val skills: List<Skill>, val languages: List<Language>)

class PersonBuilder {
    private var name: String = ""
    private var company: String = ""
    private val skillBuilder: SkillBuilder = SkillBuilder()
    private val languageBuilder: LanguageBuilder = LanguageBuilder()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(init: SkillBuilder.() -> Unit) {
        skillBuilder.init()
    }

    fun languages(init: LanguageBuilder.() -> Unit) {
        languageBuilder.init()
    }
    fun build(): Person {
        return Person(name, company, skillBuilder.build(), languageBuilder.build())
    }
}
