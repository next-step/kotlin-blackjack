package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["박민규", "차밍"])
    @ParameterizedTest
    fun name(value: String) {
        val person: Person = introduce {
            name(value)
        }

        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("박민규")
            company("인프랩")
        }

        assertThat(person.name).isEqualTo("박민규")
        assertThat(person.company).isEqualTo("인프랩")
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("박민규")
            company("인프랩")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        assertThat(person.skills.size).isEqualTo(3)
        assertThat(person.skills[0].level).isEqualTo(SkillLevel.SOFT)
        assertThat(person.skills[0].name).isEqualTo("A passion for problem solving")
        assertThat(person.skills[1].level).isEqualTo(SkillLevel.SOFT)
        assertThat(person.skills[1].name).isEqualTo("Good communication skills")
        assertThat(person.skills[2].level).isEqualTo(SkillLevel.HARD)
        assertThat(person.skills[2].name).isEqualTo("Kotlin")
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("박민규")
            company("인프랩")
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

        assertThat(person.languages.size).isEqualTo(2)
        assertThat(person.languages[0].level).isEqualTo(5)
        assertThat(person.languages[0].name).isEqualTo("Korean")
        assertThat(person.languages[1].level).isEqualTo(3)
        assertThat(person.languages[1].name).isEqualTo("English")
    }

    @Test
    fun twice() {
        introduce {
            name("A")
            company("B")
            skills {
                hard("C")
                hard("D")
                soft("E")
            }
            languages {
                "F" level 1
                "G" level 2
            }
        }
        val person: Person = introduce {
            name("박민규")
            company("인프랩")
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

        assertThat(person.name).isEqualTo("박민규")
        assertThat(person.company).isEqualTo("인프랩")
        assertThat(person.skills.size).isEqualTo(3)
        assertThat(person.skills[0].level).isEqualTo(SkillLevel.SOFT)
        assertThat(person.skills[0].name).isEqualTo("A passion for problem solving")
        assertThat(person.skills[1].level).isEqualTo(SkillLevel.SOFT)
        assertThat(person.skills[1].name).isEqualTo("Good communication skills")
        assertThat(person.skills[2].level).isEqualTo(SkillLevel.HARD)
        assertThat(person.skills[2].name).isEqualTo("Kotlin")
        assertThat(person.languages.size).isEqualTo(2)
        assertThat(person.languages[0].level).isEqualTo(5)
        assertThat(person.languages[0].name).isEqualTo("Korean")
        assertThat(person.languages[1].level).isEqualTo(3)
        assertThat(person.languages[1].name).isEqualTo("English")
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private var skills: MutableList<Skill> = mutableListOf()
    private var languages: MutableList<Language> = mutableListOf()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = (SkillBuilder().apply(block).build())
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        val person = Person(name, company, skills.toList(), languages.toList())
        skills.clear()
        return person
    }
}

data class Person(val name: String, val company: String, val skills: List<Skill>, val languages: List<Language>)
