package dsl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["홍길동", "피타팻"])
    @ParameterizedTest
    fun name(name: String) {
        val person = introduce {
            name(name)
        }
        person.name shouldBe name
    }

    @Test
    fun company() {
        val person = introduce {
            name("피타팻")
            company("우아한형제들")
        }
        person.company shouldBe "우아한형제들"
    }

    @Test
    fun skills() {
        val person = introduce {
            name("피타팻")
            company("우아한형제들")
            skills {
                soft("a passin for problem solving")
                soft("good communication skills")
                hard("Kotlin")
            }
        }

        val softSkillExpected = listOf("a passin for problem solving", "good communication skills")
        val hardSkillExpected = listOf("Kotlin")
        person.skills.softSkillList shouldBe softSkillExpected
        person.skills.hardSkillList shouldBe hardSkillExpected
    }

    @Test
    fun languages() {
        val person = introduce {
            name("피타팻")
            company("우아한형제들")
            skills {
                soft("a passin for problem solving")
                soft("good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        val expected = mapOf("Korean" to 5, "English" to 3)
        person.languages shouldBe expected
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills()
    private var languages: Languages = Languages()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit): Skills {
        return SkillsBuilder().apply(block).build().also {
            skills = it
        }
    }

    fun languages(block: LanguagesBuilder.() -> Unit): Languages {
        return LanguagesBuilder().apply(block).build().also {
            languages = it
        }
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

data class Person(val name: String, val company: String?, val skills: Skills, val languages: Languages)

class SkillsBuilder {
    private var softSkillList: List<String> = listOf()
    private var hardSkillList: List<String> = listOf()

    fun soft(value: String) {
        softSkillList += value
    }

    fun hard(value: String) {
        hardSkillList += value
    }

    fun build(): Skills = Skills(
        softSkillList = softSkillList,
        hardSkillList = hardSkillList
    )
}

data class Skills(
    val softSkillList: List<String> = listOf(),
    val hardSkillList: List<String> = listOf(),
)

class LanguagesBuilder {
    private var languages: Map<String, Int> = mapOf()

    infix fun String.level(level: Int) {
        languages = languages.plus(mapOf(this to level))
    }

    fun build() = Languages(languages)
}

data class Languages(
    val language: Map<String, Int> = mapOf()
) : Map<String, Int> by language
