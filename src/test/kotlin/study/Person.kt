package study

data class Person(val name: String, val company: String?, val skills: Skills, val languages: Languages)

class PersonDsl(private val name: String) {

    private var company: String? = null
    private var skills: Skills = Skills.empty()
    private var languages: Languages = Languages.empty()

    fun company(company: String) {
        this.company = company
    }

    fun skills(skillSupplier: SkillsDsl.() -> Unit) {
        this.skills = SkillsDsl().apply(skillSupplier).toSkills()
    }

    fun languages(languageSupplier: LanguagesDsl.() -> Unit) {
        this.languages = LanguagesDsl().apply(languageSupplier).toLanguages()
    }

    fun toPerson(): Person {
        return Person(name, company, skills, languages)
    }
}
