package dsl

/**
 * Person의 변수를 var로 변경하는 것이 아닌 빌더 이용
 */
class PersonBuilder {
    // 1차
    // private var name: String = ""
    // private var company: String = ""
    // 2차
    private lateinit var name: String
    private var company: String? = null
    private var skills: List<Skill>? = null
    private var languages: List<Language>? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

data class Person(val name: String, val company: String?, val skills: List<Skill>?, val languages: List<Language>?)
