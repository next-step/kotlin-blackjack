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
}

private fun introduce(initializer: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(initializer).build()
}

data class Person(val name:String, val company:String, val skills: Skills)

class PersonBuilder {
    private lateinit var name: String
    private var company: String = ""
    private var skills: Skills = Skills()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(initializer: Skills.() -> Unit) {
        skills = Skills().apply(initializer)
    }

    fun build() = Person(name, company, skills)
}

class Skills(val skills: MutableList<Skill> = mutableListOf()) : List<Skill> by skills {

    fun soft(name: String) {
        skills.add(Soft(name))
    }

    fun hard(name: String) {
        skills.add(Hard(name))
    }

    fun sealed(skill: Skill) {
        when(skill) {
            is Hard -> ""
            is Soft -> ""
        }
    }
}

sealed class Skill
data class Soft(val name: String) : Skill()
data class Hard(val name: String) : Skill()
