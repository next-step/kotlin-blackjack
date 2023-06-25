package learning

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val skills = mutableListOf<Skill>()
    private val languages = mutableListOf<Language>()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        val skillsBuilder = SkillsBuilder().apply(block)
        skills.addAll(skillsBuilder.skills)
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        val languageBuilder = LanguageBuilder().apply(block)
        languages.addAll(languageBuilder.languages)
    }

    fun build(): Person {
        val skills = Skills(skills)
        val languages = Languages(languages)
        return Person(name, company, skills, languages)
    }
}
