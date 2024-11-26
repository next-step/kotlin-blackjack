package dsl.builder

import dsl.Person

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: List<String> = emptyList()
    private var languages: Map<String, Int> = emptyMap()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(function: SkillsBuilder.() -> Unit) {
        val builder = SkillsBuilder().apply(function)
        skills = builder.build()
    }

    fun languages(function: LanguagesBuilder.() -> Unit) {
        val builder = LanguagesBuilder().apply(function)
        languages = builder.build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}
