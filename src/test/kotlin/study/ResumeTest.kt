package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {

    @Test
    fun name() {
        val person = introduce {
            name("고정완")
        }
        assertThat(person.name).isEqualTo("고정완")
    }

    @Test
    fun company() {
        val person = introduce {
            name("고정완")
            company("Dreamfora")
        }
        assertThat(person.company).isEqualTo("Dreamfora")
    }

    @Test
    fun soft() {
        val person = introduce {
            name("고정완")
            company("Dreamfora")
            skills {
                soft("말랑스킬")
            }
        }
        assertThat(person.skills).contains(Soft("말랑스킬"))
    }

    @Test
    fun hard() {
        val person = introduce {
            name("고정완")
            company("Dreamfora")
            skills {
                soft("말랑스킬")
                hard("딱딱스킬")
            }
        }
        assertThat(person.skills).contains(Hard("딱딱스킬"))
    }

    @Test
    fun korean() {
        val person = introduce {
            name("고정완")
            company("Dreamfora")
            skills {
                soft("말랑스킬")
                hard("딱딱스킬")
            }
            languages {
                "Korean" level 5
            }
        }
        assertThat(person.languages).contains(Language("Korean", 5))
    }

    @Test
    fun english() {
        val person = introduce {
            name("고정완")
            company("Dreamfora")
            skills {
                soft("말랑스킬")
                hard("딱딱스킬")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.languages).contains(Language("Korean", 5), Language("English", 3))
    }
}

private fun introduce(initializer: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(initializer).build()
}

data class Person(
    val name: String? = "",
    val company: String?,
    val skills: Skills? = Skills(),
    val languages: Languages? = Languages()
)

class PersonBuilder() {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = Skills()
    private var languages: Languages? = Languages()

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

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class Skills(private val skills: MutableList<Skill> = mutableListOf()) : List<Skill> by skills {
    fun soft(name: String) {
        skills.add(Soft(name))
    }

    fun hard(name: String) {
        skills.add(Hard(name))
    }
}

sealed class Skill

data class Hard(val name: String) : Skill()

data class Soft(val name: String) : Skill()

class Languages(private val languages: MutableList<Language> = mutableListOf()) : List<Language> by languages {
    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }
}

data class Language(val name: String, val level: Int)
