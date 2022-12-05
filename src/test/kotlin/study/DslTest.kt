package study

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun testIntroduce() {
        val person: Person = introduce {
            name("Mia")
            company("woowabros")
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

        person.name shouldBe "Mia"
        person.company shouldBe "woowabros"
        person.skills shouldContainAll listOf(
            Skill(SkillType.SOFT, "A passion for problem solving"),
            Skill(SkillType.SOFT, "Good communication skills"),
            Skill(SkillType.HARD, "Kotlin")
        )
        person.languages shouldContainAll listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(val name: String, val company: String, val skills: List<Skill>, val languages: List<Language>)

class PersonBuilder {
    lateinit var name: String
    lateinit var company: String
    var skills: List<Skill> = emptyList()
    var languages: List<Language> = emptyList()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: Skill.Companion.SkillsBuilder.() -> Unit) {
        this.skills = Skill.Companion.SkillsBuilder().apply(block).build()
    }

    fun languages(block: Language.Companion.LanguagesBuilder.() -> Unit) {
        this.languages = Language.Companion.LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

data class Skill(val type: SkillType, val description: String) {
    companion object {
        class SkillsBuilder {
            var skills: MutableList<Skill> = mutableListOf()
            fun soft(value: String) {
                skills.add(Skill(SkillType.SOFT, value))
            }

            fun hard(value: String) {
                skills.add(Skill(SkillType.HARD, value))
            }

            fun build(): List<Skill> {
                return skills.toList()
            }
        }
    }
}

enum class SkillType { SOFT, HARD }

data class Language(val language: String, val level: Int) {
    companion object {
        class LanguagesBuilder {
            var languages: MutableList<Language> = mutableListOf()
            infix fun String.level(level: Int) {
                languages.add(Language(this, level))
            }

            fun build(): List<Language> {
                return languages.toList()
            }
        }
    }
}
