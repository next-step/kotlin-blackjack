package com.nextstep.jngcii.dsl

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class Person(
    val name: String,
    val company: String,
    val skills: List<Skill>,
    val languages: List<Language>
)

class PersonBuilder {
    private var name: String = ""
    private var company: String? = null
    private var skills: List<Skill> = emptyList()
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
        languages = LanguageBuilder().apply(block).build()
    }

    fun build() = Person(
        name = name,
        company = company ?: "무직",
        skills = skills,
        languages = languages
    )
}
