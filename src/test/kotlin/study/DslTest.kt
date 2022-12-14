package study

import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {

    @Test
    fun introduce() {
        val person: Person = introduce {
            name("김성훈")
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

        person.name shouldBe "김성훈"
        person.company shouldBe "우아한형제들"
        person.skills shouldContainInOrder listOf(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin"),
        )
        person.languages shouldContainInOrder listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: List<Skill>
    private lateinit var languages: List<Language>

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

    fun build(): Person = Person(name, company, skills, languages)
}

class SkillBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills += Soft(value)
    }

    fun hard(value: String) {
        skills += Hard(value)
    }

    fun build(): List<Skill> = skills.toList()
}

class LanguageBuilder {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languages += Language(this, level)
    }

    fun build(): List<Language> = languages.toList()
}

data class Person(val name: String, val company: String, val skills: List<Skill>, val languages: List<Language>)

sealed interface Skill
data class Hard(val description: String) : Skill
data class Soft(val description: String) : Skill

data class Language(val language: String, val level: Int)
