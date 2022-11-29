package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/**
 introduce {
 name("김동인")
 company("ep")
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
 */
class DslTest {

    @Test
    fun introduce() {
        val person: Person = introduce {
            name("김동인")
        }
        person.name shouldBe "김동인"
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("김동인")
            company("ep")
        }
        person.name shouldBe "김동인"
        person.company shouldBe "ep"
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("김동인")
            company("ep")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "김동인"
        person.company shouldBe "ep"
        person.skills shouldBe Skills(soft = listOf("A passion for problem solving", "Good communication skills"), hard = listOf("Kotlin"))
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("김동인")
            company("ep")
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
        person.name shouldBe "김동인"
        person.company shouldBe "ep"
        person.skills shouldBe Skills(soft = listOf("A passion for problem solving", "Good communication skills"), hard = listOf("Kotlin"))
        person.languages shouldBe Languages(languages = listOf(Language("Korean", 5), Language("English", 3)))
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    val person = PersonBuilder().apply {
        block()
    }
    return person.build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String = ""
    private var skills: Skills = Skills()
    private var languages: Languages = Languages()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        val skills = SkillBuilder().apply {
            block()
        }
        this.skills = skills.build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        val languages = LanguagesBuilder().apply {
            block()
        }
        this.languages = languages.build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class SkillBuilder {
    private val soft = mutableListOf<String>()
    private val hard = mutableListOf<String>()

    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }

    fun build(): Skills {
        return Skills(soft.toList(), hard.toList())
    }
}

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(language = this, level = level))
    }

    fun build(): Languages {
        return Languages(languages = languages.toList())
    }
}

data class Person(val name: String, val company: String, val skills: Skills, val languages: Languages)
data class Skills(val soft: List<String> = listOf(), val hard: List<String> = listOf())
data class Languages(val languages: List<Language> = listOf())
data class Language(val language: String, val level: Int)
