package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {
    @Test
    fun name() {
        val person: Person = introduce {
            name("전영상")
        }
        assertThat(person.name).isEqualTo("전영상")
    }

    @Test
    fun compnay() {
        val person: Person = introduce {
            company("null")
        }
        assertThat(person.company).isEqualTo("null")
    }

    @Test
    fun hard() {
        val person: Person = introduce {
            skills {
                hard("Kotlin")
            }
        }
        assertThat(person.skills.toList()).contains(Hard("Kotlin"))
    }

    @Test
    fun soft() {
        val person: Person = introduce {
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
            }
        }
        assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Soft("A passion for problem solving"),
            Soft("Good communication skills")
        )
    }

    @Test
    fun language() {
        val person: Person = introduce {
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.languages.toList()).containsExactlyInAnyOrder(
            Language(Pair("Korean", 5)),
            Language(Pair("English", 3))
        )
    }
}

fun introduce(initializer: Person.() -> Unit): Person {
    return Person().apply { initializer() }
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

sealed class Skill
data class Hard(val name: String) : Skill()
data class Soft(val name: String) : Skill()

class Languages() {
    private val value: MutableList<Language> = mutableListOf()
    infix fun String.level(level: Int) {
        value.add(Language(this to level))
    }

    fun toList(): List<Language> {
        return value
    }
}

data class Language(private val value: Pair<String, Int>)
