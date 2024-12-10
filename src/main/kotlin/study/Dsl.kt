package study

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(val name: String, val company: String?, val skills: List<Skill>, val languages: List<Language>)

class PersonBuilder {
    private var name: String = "홍길동"
    private var company: String? = null

    private val languagesBuilder = LanguagesBuilder()
    private val skillsBuilder = SkillsBuilder()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skillsBuilder.apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languagesBuilder.apply(block)
    }

    fun build(): Person {
        return Person(name, company, skillsBuilder.build(), languagesBuilder.build())
    }
}

data class Language(val name: String, val level: Int)

class LanguagesBuilder {
    private val languages = ArrayDeque<Language>()

    infix fun String.level(level: Int) = languages.add(Language(this, level))

    fun build(): List<Language> {
        return languages.toList()
    }
}

data class Skill(val name: String, val description: String)

class SkillsBuilder {
    private val skills = ArrayDeque<Skill>()

    fun soft(desc: String) {
        skills.add(Skill("soft", desc))
    }

    fun hard(desc: String) {
        skills.add(Skill("hard", desc))
    }

    fun build(): List<Skill> {
        return skills.toList()
    }
}
