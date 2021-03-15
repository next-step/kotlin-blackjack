package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {

    @Test
    fun name() {
        val person: Person = introduce {
            name("이름")
            company("회사명")
            skills {
                soft("A")
                soft("B")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.name).isEqualTo("이름")
        assertThat(person.company).isEqualTo("회사명")
        assertThat(person.skills.values).containsExactlyInAnyOrder(Soft("A"), Soft("B"), Hard("Kotlin"))
        assertThat(person.languages.values).containsKey("Korean")
    }
}

fun introduce(initializer: Person.() -> Unit): Person {
    return Person().apply(initializer)
}

class Person {
    lateinit var name: String
    var company: String? = null
    var skills: Skills = Skills()
    var languages: Languages = Languages()

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
    var values: MutableList<Skill> = mutableListOf()

    fun soft(soft: String) {
        values.add(Soft(soft))
    }

    fun hard(hard: String) {
        values.add(Hard(hard))
    }
}

sealed class Skill

data class Soft(val soft: String) : Skill()

data class Hard(val hard: String) : Skill()

class Languages {
    var values: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(level: Int) {
        values[this] = level
    }
}
