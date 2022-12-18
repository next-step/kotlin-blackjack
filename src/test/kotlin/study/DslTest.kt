package study

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
        introduce {
            name("이름")
            company("회사")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    val personBuilder: PersonBuilder = PersonBuilder()
    personBuilder.block()
    return personBuilder.build()
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
    private lateinit var soft: String
    private lateinit var hard: String

    fun soft(value: String) {
        this.soft = value
    }

    fun hard(value: String) {
        this.hard = value
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}

data class Person(val name: String, val company: String?, val skills: Skills)

data class Skills(val soft: String, val hard: String)