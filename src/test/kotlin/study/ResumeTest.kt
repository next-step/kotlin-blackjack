package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {
    @Test
    fun name() {
       val person =  introduce {
            name("장세훈")
        }
        assertThat(person.name).isEqualTo("장세훈")
    }

    @Test
    fun company() {
        val person =  introduce {
            name("장세훈")
            company("회사")
        }
        assertThat(person.company).isEqualTo("회사")
    }

    @Test
    fun soft() {
        val person =  introduce {
            name("장세훈")
            company("회사")
            skills {
                soft("passion")
            }
        }
        assertThat(person.skills.skills).contains(Soft("passion"))
    }

    @Test
    fun hard() {
        val person =  introduce {
            name("장세훈")
            company("회사")
            skills {
                soft("passion")
                hard("Kotlin")
            }
        }
        assertThat(person.skills.skills).contains(Hard("Kotlin"))
    }

}

private fun introduce(initializer: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(initializer).build()
}

data class Person(val name: String, val company: String?, val skills: Skills)

class PersonBuilder{
    lateinit var name: String
    private var company: String? = null
    private var skills: Skills = Skills()

    fun name(name: String){
        this.name = name
    }
    fun company(company: String){
        this.company = company
    }
    fun skills(initializer: Skills.() -> Unit){
    skills = Skills().apply(initializer)
    }
    fun build(): Person = Person(name , company, skills)
}

class Skills(val skills: MutableList<Skill> = mutableListOf()): List<Skill> by skills {

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