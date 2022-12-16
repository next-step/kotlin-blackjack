package study

import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun introduce() {
        val person: Person = introduce {
            name("김다정")
        }
        person.name shouldBe "김다정"
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("김다정")
            company("우아한형제들")
        }
        person.name shouldBe "김다정"
        person.company shouldBe "우아한형제들"
    }

    @Test
    fun detail_introduce() {
        listOf(1, 2, 3).map { }
        val person: Person = introduce {
            name("김다정")
            company("우아한형제들")
            skills {
                soft("Eating")
                soft("Sleeping")
                hard("Kotlin")
            }
            languages {
                "Korean" level 2
                "English" level -1
            }
        }
        person.name shouldBe "김다정"
        person.company shouldBe "우아한형제들"
        person.skills.hardSkills shouldContainInOrder listOf("Kotlin")
        person.skills.softSkills shouldContainInOrder listOf("Eating", "Sleeping")
        person.languages shouldContainInOrder listOf(Language("Korean", 2), Language("English", -1))
    }
}

fun introduce(function: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(function).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills(emptyList(), emptyList())
    private val languagesBuilder = LanguagesBuilder()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(function: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(function).build()
    }

    fun languages(init: LanguagesBuilder.() -> Unit) {
        languagesBuilder.apply(init)
    }

    fun build(): Person {
        val languages = languagesBuilder.build()
        return Person(name, company, skills, languages)
    }
}

class SkillsBuilder {
    private val sortSkills = mutableListOf<String>()
    private val hartSkills = mutableListOf<String>()

    fun soft(value: String) {
        sortSkills.add(value)
    }

    fun hard(value: String) {
        hartSkills.add(value)
    }

    fun build(): Skills {
        return Skills(sortSkills, hartSkills)
    }
}

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    fun build(): List<Language> {
        return languages.toList()
    }

    infix fun String.level(level: Int) {
        languages += Language(this, level)
    }
}

data class Skills(val softSkills: List<String>, val hardSkills: List<String>)

data class Person(val name: String, val company: String? = "우아한형제들", val skills: Skills = Skills(emptyList(), emptyList()), val languages: List<Language> = emptyList())

data class Language(val language: String, val level: Int)
