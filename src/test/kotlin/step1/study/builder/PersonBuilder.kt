package step1.study.builder

import step1.study.model.Person

/**
 * 사람 객체 빌더
 * */
class PersonBuilder {

    private var name: String = DEFAULT_NAME
    private var company: String = DEFAULT_COMPANY
    private var skills: MutableList<Pair<String, String>> = mutableListOf()
    private var languages: MutableMap<String, Int> = mutableMapOf()
    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: PersonBuilder.() -> Unit) {
        this.block()
    }

    fun languages(block: PersonBuilder.() -> Unit) {
        this.block()
    }

    fun soft(description: String) {
        skills.add(SKILL_LEVEL_SOFT_KEY to description)
    }

    fun hard(description: String) {
        skills.add(SKILL_LEVEL_HARD_KEY to description)
    }

    fun build() = Person(name, company, skills, languages)

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    companion object {
        private const val DEFAULT_NAME = ""
        private const val DEFAULT_COMPANY = ""
        private const val SKILL_LEVEL_SOFT_KEY = "SKILL_LEVEL_SOFT_KEY"
        private const val SKILL_LEVEL_HARD_KEY = "SKILL_LEVEL_HARD_KEY"
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
