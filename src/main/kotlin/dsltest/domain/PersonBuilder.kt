package dsltest.domain

class PersonBuilder {
    private var name: String = ""
    private var company: String? = null
    private var skillBuilder = SkillBuilder()
    private var languageBuilder = LanguageBuilder()
    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(init: SkillBuilder.() -> Unit) {
        return skillBuilder.init()
    }

    fun languages(init: LanguageBuilder.() -> Unit) {
        return languageBuilder.init()
    }

    fun build() = Person(name, company, skillBuilder.build(), languageBuilder.build())
}
