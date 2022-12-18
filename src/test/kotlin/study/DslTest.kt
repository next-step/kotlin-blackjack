package study

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/*
introduce {
  name("이름")
  company("회사")
  skills {
    soft("A passion for problem solving")
    soft("Good communication skills")
    hard("Kotlin")
  }
  languages {
    "Korean" level 5
    "English" level 3
  }
}
 */
class DslTest {
    @Test
    fun introduce() {
        val person: Person = introduce {
            name("이름")
            skills {
                soft("test")
                soft("test")
                hard("test")
            }
        }
        person.name shouldBe "이름"
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("이름")
            company("회사")
            skills {
                soft("test")
                soft("test")
                hard("test")
            }
        }
        person.name shouldBe "이름"
        person.company shouldBe "회사"
    }

    @Test
    fun skills() {
        val person = introduce {
            name("이름")
            company("회사")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.skills.soft shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.skills.hard shouldBe listOf("Kotlin")
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private lateinit var skills: Skills

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills =  SkillsBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills)
    }
}

class SkillsBuilder {
    private var soft: MutableList<String> = mutableListOf()
    private var hard: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        this.soft.add(value)
    }

    fun hard(value: String) {
        this.hard.add(value)
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}

data class Person(val name: String, val company: String?, val skills: Skills)

data class Skills(val soft: List<String>, val hard: List<String>)