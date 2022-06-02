package dsl

open class ResumeBuilder {
    private lateinit var name: String
    private var company: String = ""
    private var skills: Skills? = null
    private var languages: Languages? = null
    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Resume {
        return Resume(name, company, skills, languages)
    }
}
