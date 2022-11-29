package study

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/*
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
 */

internal class DslTest {

    @Test
    fun intro() {
        val person: Person = introduce {
            name("황재우")
            company("Google")
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

        person.name shouldBe "황재우"
        person.company shouldBe "Google"
        person.skills.softSkills shouldContainExactlyInAnyOrder listOf(
            "A passion for problem solving",
            "Good communication skills"
        )
        person.languages shouldContainExactly listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}

private fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skill: Skill
    private lateinit var language: List<Language>

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skill = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        language = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skill, language)
    }
}

class SkillBuilder {
    private var softSkills: MutableList<String> = mutableListOf()
    private var hardSkills: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    fun build(): Skill {
        return Skill(softSkills.toList(), hardSkills.toList())
    }
}

class LanguageBuilder {
    private var languages: MutableList<Language> = mutableListOf()

    infix fun String.level(value: Int) {
        languages.add(Language(this, value))
    }

    fun build(): List<Language> {
        return languages.toList()
    }
}

data class Skill(val softSkills: List<String>, val hardSkills: List<String>)
data class Language(val name: String = "", val level: Int = 0)
data class Person(val name: String, val company: String, val skills: Skill, val languages: List<Language>)
