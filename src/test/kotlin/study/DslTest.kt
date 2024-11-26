package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("이민수")
 *   company("활빈당")
 *   skills {
 *     soft("A passion for problem solving")
 *     soft("Good communication skills")
 *     hard("Kotlin")
 *   }
 *   languages {
 *     "Korean" level 5
 *     "English" level 3
 *   }
 * }
 *
 */
class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["홍길동", "홍", "이민수", "테스터"])
    fun name1(name: String) {
        val person = introduce {
            this.name(name)
            this.company("활빈당")
        }
        person.name shouldBe name
    }

    @ParameterizedTest
    @ValueSource(strings = ["활빈당", "홍", "이민수", "테스터"])
    fun company1(company: String) {
        val person = introduce {
            this.company(company)
        }
        person.company shouldBe company
    }

    @Test
    fun skills1() {
        val person = introduce {
            skills {

            }
        }
    }
}

class Person(
    var name: String = "",
    var company: String = ""
) {
    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: )
}
fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val skills: MutableList<Skill> = mutableListOf()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun soft(description: String) {
        skills.add(SoftSkill(description))
    }

    fun hard(description: String) {
        skills.add(HardSkill(description))
    }

    fun build(): Person {
        require(!company.isNullOrEmpty()) { "" }
        return Person(name, company!!)
    }
}

interface Skill {
    val description: String
}

data class SoftSkill(override val description: String = ""): Skill

data class HardSkill(override val description: String = ""): Skill

data class Skills(val skills: MutableList<Skill>) {
    fun soft(description: String) {
        skills.add(SoftSkill(description))
    }
    fun hard(description: String) {
        skills.add(HardSkill(description))
    }
}

