package dsl

class Person(val name: String, val company: String?, val skillBuilder: SkillBuilder, val languageBuilder: LanguageBuilder)

class PersonBuilder {
    lateinit var name: String
    var company: String? = null
    var skillBuilder: SkillBuilder = SkillBuilder()
    var languageBuilder: LanguageBuilder = LanguageBuilder()
    fun name(value: String) {
        this.name = value
    }

    fun company(value: String? = null) {
        this.company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        val skillBuilder = SkillBuilder().apply(block)
        this.skillBuilder = skillBuilder
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        this.languageBuilder = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skillBuilder, languageBuilder)
    }
}
