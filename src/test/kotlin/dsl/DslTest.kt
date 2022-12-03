package dsl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

/*
dsl.introduce {
  name("최차영")
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
    @ValueSource(strings = ["최차영", "이든"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person = introduce {
            name(value)
        }
        person.name shouldBe value
    }

    @CsvSource("최차영, 회사")
    @ParameterizedTest
    fun company(name: String, company: String) {
        val person = introduce {
            name(name)
            company(company)
        }
        person.name shouldBe name
        person.company shouldBe company
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("최차영")
            company("회사")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.skills?.soft shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.skills?.hard shouldBe listOf("Kotlin")
    }

    @Test
    fun languages() {
        val person = introduce {
            name("최차영")
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

        person.languages shouldBe Languages(
            listOf(
                Language("Korean", 5),
                Language("English", 3)
            )
        )
    }
}
