package study

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ResumeTest {
    @Test
    fun name() {
        val person: Person = introduce {
            name("이승희")
        }

        Assertions.assertThat(person.name).isEqualTo("이승희")
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("이승희")
            company("42dot")
        }

        Assertions.assertThat(person.name).isEqualTo("이승희")
        Assertions.assertThat(person.company).isEqualTo("42dot")
    }

    @Test
    fun hard() {
        val person: Person = introduce {
            name("이승희")
            company("42dot")
            skills {
                hard("Kotlin")
            }
        }

        Assertions.assertThat(person.name).isEqualTo("이승희")
        Assertions.assertThat(person.company).isEqualTo("42dot")
        Assertions.assertThat(person.skills.toList()).contains(Hard("Kotlin"))
    }

    @Test
    fun soft() {
        val person: Person = introduce {
            name("이승희")
            company("42dot")
            skills {
                hard("Kotlin")
                soft("A passion for problem solving")
            }
        }

        Assertions.assertThat(person.name).isEqualTo("이승희")
        Assertions.assertThat(person.company).isEqualTo("42dot")
        Assertions.assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Hard("Kotlin"),
            Soft("A passion for problem solving")
        )
    }

    @Test
    fun language() {
        val person: Person = introduce {
            name("이승희")
            company("42dot")
            skills {
                hard("Kotlin")
                soft("A passion for problem solving")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        Assertions.assertThat(person.name).isEqualTo("이승희")
        Assertions.assertThat(person.company).isEqualTo("42dot")
        Assertions.assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Hard("Kotlin"),
            Soft("A passion for problem solving")
        )
        Assertions.assertThat(person.languages.toList()).containsExactlyInAnyOrder(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}

private fun introduce(initializer: Person.() -> Unit): Person {
    return Person().apply(initializer)
}

class Person {
    lateinit var name: String
    var company: String? = null
        private set
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

class Languages {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(grade: Int) {
        languages.add(Language(this, grade))
    }

    fun toList(): List<Language> {
        return languages.toList()
    }
}

sealed class Skill // sealed 클래스는 외부에서 상속 불가

data class Hard(val name: String) : Skill()

data class Soft(val name: String) : Skill()

data class Language(val language: String, val grade: Int) : Skill()
