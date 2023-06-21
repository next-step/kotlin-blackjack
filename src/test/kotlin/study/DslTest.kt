package study

import Hard
import Language
import Person
import Soft
import introduce
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("홍길동")
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
    @ValueSource(strings = ["oh", "sung", "oh"])
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
            name("ohsung")
            company("sk")
        }

        person.name shouldBe "ohsung"
        person.company shouldBe "sk"
    }

    @Test
    fun skills() {
        val person = introduce {
            name("ohsung")
            company("sk")
            skills {
                soft("A passion for problem solving")
                hard("Java")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.skills.list[0].value shouldBe "A passion for problem solving"
        person.skills.list[0].shouldBeTypeOf<Soft>()

        person.skills.list[1].value shouldBe "Java"
        person.skills.list[1].shouldBeTypeOf<Hard>()

        person.skills.list[2].value shouldBe "Good communication skills"
        person.skills.list[2].shouldBeTypeOf<Soft>()

        person.skills.list[3].value shouldBe "Kotlin"
        person.skills.list[3].shouldBeTypeOf<Hard>()
    }

    @Test
    fun languages() {
        val person = introduce {
            name("ohsung")
            company("sk")
            skills {
                soft("A passion for problem solving")
                hard("Java")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
                "Spanish" level 0
            }
        }

        person.languages.list[0].country shouldBe "Korean"
        person.languages.list[0].level shouldBe 5
        person.languages.list[0].shouldBeTypeOf<Language>()

        person.languages.list[1].country shouldBe "English"
        person.languages.list[1].level shouldBe 3

        person.languages.list[2].country shouldBe "Spanish"
        person.languages.list[2].level shouldBe 0
    }
}
