package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {

    @Test
    fun name() {
        val person: Person = introduce {
            name("황준오")
        }
        assertThat(person.name).isEqualTo("황준오")
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("황준오")
            company("준오컴퍼니")
        }
        assertThat(person.name).isEqualTo("황준오")
        assertThat(person.company).isEqualTo("준오컴퍼니")
    }

    @Test
    fun hard() {
        val person: Person = introduce {
            name("황준오")
            company("준오컴퍼니")
            skills {
                hard("Kotlin")
                soft("A passion for Eating")
                soft("soft is not hard!?")
            }
        }
        assertThat(person.name).isEqualTo("황준오")
        assertThat(person.company).isEqualTo("준오컴퍼니")
        assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Hard("Kotlin"),
            Soft("A passion for Eating"),
            Soft("soft is not hard!?")
        )
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("황준오")
            company("준오컴퍼니")
            skills {
                hard("Kotlin")
                soft("A passion for Eating")
                soft("soft is not hard!?")
            }
            languages {
                "Korean" level 5
                "English" level 1
            }
        }
        assertThat(person.name).isEqualTo("황준오")
        assertThat(person.company).isEqualTo("준오컴퍼니")
        assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Hard("Kotlin"),
            Soft("A passion for Eating"),
            Soft("soft is not hard!?")
        )
        println(person.languages.toList())
        assertThat(person.languages.toList()).containsExactlyInAnyOrder(
            Language("Korean", 5),
            Language("English", 1)
        )
    }
}

fun introduce(initializer: Person.() -> Unit): Person {
    return Person().apply(initializer)
}

class Person {
    lateinit var name: String
    var company: String? = null
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
        languages = Languages().apply(initializer)
    }
}

class Skills {
    private val skills: MutableList<Skill> = mutableListOf()

    fun hard(name: String) = skills.add(Hard(name))

    fun soft(name: String) = skills.add(Soft(name))

    fun toList(): List<Skill> {
        return skills
    }
}

sealed class Skill

data class Hard(val name: String) : Skill()
data class Soft(val name: String) : Skill()

class Languages {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) = languages.add(Language(this, level))

    fun toList(): List<Language> {
        return languages
    }
}

data class Language(val name: String, val level: Int)
