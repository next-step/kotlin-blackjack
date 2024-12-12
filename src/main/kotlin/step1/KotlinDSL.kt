package step1

fun doDsl() {
    introduce {
        name("박재성")
        company("우아한형제들")
        skills {
            soft("A passion for problem solving")
            soft("Good communication skills")
            hard("Kotlin")
        }
        languages {
            "Korean" level 5
            "English" level 3
        }
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun build(): Person {
        return Person(name, company)
    }
}

data class Person(val name: String, val company: String?)

fun skills(block: SkillBuilder.() -> Unit): Skill {
    return SkillBuilder().apply(block).build()
}

class SkillBuilder {
    private val soft: MutableList<String> = mutableListOf()
    private val hard: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }

    fun build(): Skill {
        return Skill(soft, hard)
    }
}

data class Skill(val soft: List<String>, val hard: List<String>)

fun languages(block: LanguageBuilder.() -> Unit): List<Language> {
    return LanguageBuilder().apply(block).build()
}

class LanguageBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(value: Int) {
        languages.add(Language(this, value))
    }

    fun build(): List<Language> = languages
}

data class Language(
    val name: String,
    val level: Int,
)
