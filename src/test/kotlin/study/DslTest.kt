package study

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun introduce() {
        val person: Person = introduce {
            name("최정")
        }
        person.name shouldBe "최정"
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("최정")
            company("알티미디어")
        }
        person.name shouldBe "최정"
        person.company shouldBe "알티미디어"
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("최정")
            company("알티미디어")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "최정"
        person.company shouldBe "알티미디어"
        person.skills shouldBe Skills(
            listOf(
                Skill.Soft(listOf("A passion for problem solving", "Good communication skills")),
                Skill.Hard(listOf("Kotlin"))
            )
        )
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("최정")
            company("알티미디어")
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
        person.name shouldBe "최정"
        person.company shouldBe "알티미디어"
        person.skills shouldBe Skills(
            listOf(
                Skill.Soft(listOf("A passion for problem solving", "Good communication skills")),
                Skill.Hard(listOf("Kotlin"))
            )
        )
        person.languages shouldBe Languages(
            listOf(
                Language("Korean", 5),
                Language("English", 3)
            )
        )
    }
}
