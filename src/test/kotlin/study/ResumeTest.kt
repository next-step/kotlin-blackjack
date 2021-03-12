package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {
    @Test
    fun name() {
        val person: Person = introduce {
            name("오길환")
        }

        assertThat(person.name).isEqualTo("오길환")
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("오길환")
            company("구글")
        }

        assertThat(person.name).isEqualTo("오길환")
        assertThat(person.company).isEqualTo("구글")
    }

    @Test
    fun hard() {
        val person: Person = introduce {
            name("오길환")
            company("구글")
            skills {
                hard("Kotlin")
            }
        }

        assertThat(person.name).isEqualTo("오길환")
        assertThat(person.company).isEqualTo("구글")
        assertThat(person.skills.toList()).contains(Hard("Kotlin"))
    }

    @Test
    fun soft() {
        val person: Person = introduce {
            name("오길환")
            company("구글")
            skills {
                hard("Kotlin")
                soft("Java")
                soft("coding")
            }
        }

        assertThat(person.name).isEqualTo("오길환")
        assertThat(person.company).isEqualTo("구글")
        assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Hard("Kotlin"),
            Soft("Java"),
            Soft("coding")
        )
    }

    @Test
    fun lang() {
        val person: Person = introduce {
            name("오길환")
            company("구글")
            skills {
                hard("Kotlin")
                soft("Java")
                soft("coding")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.name).isEqualTo("오길환")
        assertThat(person.company).isEqualTo("구글")
        assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Hard("Kotlin"),
            Soft("Java"),
            Soft("coding")
        )
        assertThat(person.languages.toList()).containsExactlyInAnyOrder(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}

fun introduce(initializer: Person.() -> Unit): Person {
    return Person().apply(initializer)
}

class Person {
    lateinit var name: String
    lateinit var company: String
    lateinit var skills: Skills
    lateinit var languages: Languages
    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(initializer: Skills.() -> Unit) {
        skills = Skills().apply(initializer)
    }

    fun languages(initializer: Languages.() -> Unit) {
        this.languages = Languages().apply(initializer)
    }
}

class Skills {
    val skills: MutableList<Skill> = mutableListOf()

    fun hard(name: String) {
        skills.add(Hard(name))
    }

    fun soft(name: String) {
        skills.add(Soft(name))
    }

    fun toList(): List<Skill> {
        return skills
    }
}

abstract class Skill

data class Hard(val name: String) : Skill()
data class Soft(val name: String) : Skill()

class Languages {
    private val languages: MutableList<Language> = mutableListOf()
    fun toList(): List<Language> {
        return languages
    }

    infix fun String.level(other: Int): Language {
        val language = Language(this, other)
        languages.add(language)

        return language
    }
}

data class Language(val name: String, val Level: Int)
