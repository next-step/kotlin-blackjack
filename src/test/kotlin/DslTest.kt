package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {

    @ValueSource(strings = ["홍길동", "박준형"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        person.name shouldBe value
    }

    @ValueSource(strings = ["카카오", "우아한형제들"])
    @ParameterizedTest
    fun company(value: String) {
        val person = introduce {
            name("박준형")
            company(value)
        }
        person.name shouldBe "박준형"
        person.company shouldBe value
    }

    @Test
    fun skills() {
        val person = introduce {
            name("박준형")
            company("카카오")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.name shouldBe "박준형"
        person.company shouldBe "카카오"
        person.skills shouldBe listOf(
            Skill("A passion for problem solving"),
            Skill("Good communication skills"),
            Skill("Kotlin")
        )
    }

    @Test
    fun languages() {
        val person = introduce {
            name("박준형")
            company("카카오")
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

        person.name shouldBe "박준형"
        person.company shouldBe "카카오"
        person.skills shouldBe listOf(
            Skill("A passion for problem solving"),
            Skill("Good communication skills"),
            Skill("Kotlin")
        )
        person.languages shouldBe listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}
