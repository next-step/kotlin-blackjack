import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {
    @Test
    fun name() {
        val person = introduce {
            name("정상훈")
        }
        assertThat(person.name).isEqualTo("정상훈")
    }

    @Test
    fun company() {
        val person = introduce {
            name("정상훈")
            company("마드라스체크")
        }
        assertThat(person.company).isEqualTo("마드라스체크")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("정상훈")
            company("마드라스체크")
            skills {
                soft ("A passion for problem solving")
                soft ("Good communication skills")
                hard ("Kotlin")
            }
        }
        assertThat(person.skills).contains(Hard("Kotlin"))
    }

    @Test
    fun languages() {
        val person = introduce {
            name("정상훈")
            company("마드라스체크")
            skills {
                soft ("A passion for problem solving")
                soft ("Good communication skills")
                hard ("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.languages).contains(Pair("Korean", 5))
    }
}

private fun introduce(initializer: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(initializer).build()
}

data class Person(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: Languages
)

class PersonBuilder {
    private var name: String = ""
    private var company: String = ""
    private var skills: Skills = Skills()
    private var languages: Languages = Languages()

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

    fun build() = Person(name, company, skills, languages)
}

class Languages(private val languages: MutableList<Pair<String, Int>> = mutableListOf()) : List<Pair<String, Int>> by languages {
    infix fun String.level(other: Int) = languages.add(Pair(this, other))
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
data class Soft(val name: String) : Skill()
data class Hard(val name: String) : Skill()
