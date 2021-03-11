package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {
    @Test
    fun name() {
        val person: Person = introduce {
            name("이태훈")
        }

        assertThat(person.name).isEqualTo("이태훈")
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("이태훈")
            company("메쉬코리아")
        }

        assertThat(person.name).isEqualTo("이태훈")
        assertThat(person.company).isEqualTo("메쉬코리아")
    }

    @Test
    fun hard() {
        val person: Person = introduce {
            name("이태훈")
            company("메쉬코리아")
            skills {
                hard("kotlin")
            }
        }

        assertThat(person.name).isEqualTo("이태훈")
        assertThat(person.company).isEqualTo("메쉬코리아")
        assertThat(person.skills.toList()).contains(Hard("kotlin"))
    }

    @Test
    fun soft() {
        val person: Person = introduce {
            name("이태훈")
            company("메쉬코리아")
            skills {
                hard("kotlin")
                soft("Good communication skills")
            }
        }

        assertThat(person.name).isEqualTo("이태훈")
        assertThat(person.company).isEqualTo("메쉬코리아")
        assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Hard("kotlin"),
            Soft("Good communication skills")
        )
    }

    @Test
    fun language() {
        val person: Person = introduce {
            name("이태훈")
            company("메쉬코리아")
            skills {
                hard("kotlin")
                soft("Good communication skills")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(person.name).isEqualTo("이태훈")
        assertThat(person.company).isEqualTo("메쉬코리아")
        assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Hard("kotlin"),
            Soft("Good communication skills")
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
    var company: String? = ""
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
        return skills.toList()
    }
}

sealed class Skill()

data class Hard(val name: String) : Skill()

data class Soft(val name: String) : Skill()

class Languages {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun toList(): List<Language> {
        return languages.toList()
    }
}

data class Language(val name: String, val level: Int)
