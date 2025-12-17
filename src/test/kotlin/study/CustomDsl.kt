package study

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class Person(val name: String, val company: String?, val skills: Skills?, val languages: Languages?)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: Languages? = null

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

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class Skills(val softSkills: List<String>, val hardSkills: List<String>)

class SkillBuilder {
    private var softSkills: List<String> = listOf()
    private var hardSkills: List<String> = listOf()

    fun soft(value: String) {
        softSkills += value
    }

    fun hard(value: String) {
        hardSkills += value
    }

    fun build(): Skills {
        return Skills(softSkills, hardSkills)
    }
}

class Languages(val languages: List<Pair<String, Int>>)

class LanguageBuilder {
    private var languages: List<Pair<String, Int>> = listOf()

    infix fun String.level(other: Int) {
        val newPair = this to other
        languages += (newPair)
    }

    fun build(): Languages {
        return Languages(languages)
    }
}
