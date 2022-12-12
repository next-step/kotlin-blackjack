package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/*
introduce {
  name("손진영")
  company("코나아이")
  skills {
    soft("A passion for problem-solving")
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
            name("손진영")
        }
        person.name shouldBe "손진영"
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("손진영")
            company("코나아이")
        }
        person.name shouldBe "손진영"
        person.company shouldBe "코나아이"
    }
}

fun introduce(block: PersonBuilder.() -> Unit) = PersonBuilder().apply(block).build()

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun build() = Person(name, company)
}

data class Person(val name: String, val company: String)
