package study.builder

import study.Person
import study.Skill

class PersonBuilder {
    private var name: String? = null
    private var company: String? = null
    private var skills: List<Skill>? = null
    private val languages: MutableList<Pair<String, Int>> = mutableListOf()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: PersonBuilder.() -> Unit) {
        block()
    }

    infix fun String.level(other: Int) {
        languages.add(this to other)
    }

    fun build(): Person {
        return Person(
            name = name ?: throw IllegalArgumentException("이름은 필수 값입니다"),
            company = company,
            skills = skills,
            languages = mapOf(*languages.toTypedArray())
        )
    }
}
