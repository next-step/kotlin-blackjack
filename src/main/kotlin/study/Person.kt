package study

class Person {
    lateinit var name: String
    lateinit var company: String
    lateinit var skills: List<Skill>
    lateinit var languages: List<Language>

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) = SkillsBuilder()
        .apply(block).build().let { skills = it }

    fun languages(block: LanguagesBuilder.() -> Unit) = LanguagesBuilder().apply(block).build().let { languages = it }
}

fun introduce(block: Person.() -> Unit): Person = Person().apply(block)
