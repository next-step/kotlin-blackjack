package dsl

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/*
introduce {
            name("최원준")
            company("메쉬코리아")
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
            name("최원준")
        }
        person.name shouldBe "최원준"
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("최원준")
            company("메쉬코리아")
        }
        person.name shouldBe "최원준"
        person.company shouldBe "메쉬코리아"
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("최원준")
            company("메쉬코리아")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "최원준"
        person.company shouldBe "메쉬코리아"
        person.skills.values[0].name shouldBe "A passion for problem solving"
        person.skills.values[0] is Soft
        person.skills.values[1].name shouldBe "Good communication skills"
        person.skills.values[1] is Soft
        person.skills.values[2].name shouldBe "Kotlin"
        person.skills.values[2] is Hard
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("최원준")
            company("메쉬코리아")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        person.name shouldBe "최원준"
        person.company shouldBe "메쉬코리아"
        person.languages.values[0].name shouldBe "Korean"
        person.languages.values[0].level shouldBe 5
        person.languages.values[1].name shouldBe "English"
        person.languages.values[1].level shouldBe 3
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Languages {
        return Languages(languages.toList())
    }
}

class SkillsBuilder {
    private val skills = mutableListOf<Skill>()

    fun soft(value: String) {
        skills.add(Soft(value))
    }

    fun hard(value: String) {
        skills.add(Hard(value))
    }

    fun build(): Skills {
        return Skills(skills.toList())
    }
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var languages: Languages = Languages()
    private var skills: Skills = Skills()

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

data class Person(val name: String, val company: String?, val skills: Skills, val languages: Languages)
data class Languages(val values: List<Language> = listOf())
data class Language(val name: String, val level: Int)
data class Skills(val values: List<Skill> = listOf())

sealed class Skill(open val name: String)
data class Hard(override val name: String) : Skill(name)
data class Soft(override val name: String) : Skill(name)
