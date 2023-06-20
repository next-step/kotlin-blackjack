package study

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val skillsList = mutableListOf<Skill>()
    private val languagesList = mutableListOf<LanguageLevel>()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        val skillsBuilder = SkillsBuilder()
        skillsBuilder.block()
        skillsList.addAll(skillsBuilder.skillsList)
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        val languagesBuilder = LanguagesBuilder()
        languagesBuilder.block()
        languagesList.addAll(languagesBuilder.languagesList)
    }


    fun build(): Person {
        val skills = Skills(skillsList)
        val languages = Languages(languagesList)
        return Person(name, company, skills, languages)
    }
}