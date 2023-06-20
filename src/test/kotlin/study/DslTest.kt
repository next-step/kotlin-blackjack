package study

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("송시은")
 *   company("우아한형제들")
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
 */

class DslTest {
    @ValueSource(strings = ["홍길동", "송시은"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        person.name shouldBe value
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person = introduce {
            name("송시은")
            company("우아한형제들")
        }
        person.name shouldBe "송시은"
        person.company shouldBe "우아한형제들"
    }

    @Test
    fun skills() {
        val person = introduce {
            name("송시은")
            company("우아한형제들")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "송시은"
        person.company shouldBe "우아한형제들"

        person.skills.first().shouldBeInstanceOf<Skill.SoftSkill>()
        person.skills.map { it.value } shouldBe listOf(
            "A passion for problem solving",
            "Good communication skills",
            "Kotlin"
        )
    }

    @Test
    fun languages() {
        val person = introduce {
            name("송시은")
            company("우아한형제들")
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
        person.name shouldBe "송시은"
        person.company shouldBe "우아한형제들"

        person.skills.first().shouldBeInstanceOf<Skill.SoftSkill>()
        person.skills.first().value shouldBe "A passion for problem solving"

        person.languages.map { it.language } shouldBe listOf("Korean", "English")
        person.languages.map { it.level } shouldBe listOf(5, 3)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
