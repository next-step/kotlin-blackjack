package dsl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

//introduce {
//    name("박재성")
//    company("우아한형제들")
//    skills {
//        soft("A passion for problem solving")
//        soft("Good communication skills")
//        hard("Kotlin")
//    }
//    languages {
//        "Korean" level 5
//        "English" level 3
//    }
//}
class DslTest {

    @ValueSource(strings = ["정석준", "Dino"])
    @ParameterizedTest
    internal fun introduce(value: String) {
        val person: Person = introduce {
            name = value
        }
        person.name shouldBe value
        person.company shouldBe null
    }

    @Test
    internal fun company() {
        val person = introduce {
            name = "정석준"
            company = "비바리퍼블리카"
        }
        person.name shouldBe "정석준"
        person.company shouldBe "비바리퍼블리카"
    }

    @Test
    internal fun skill() {
        val person = introduce {
            name = "정석준"
            company = "비바리퍼블리카"
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "정석준"
        person.company shouldBe "비바리퍼블리카"
        person.skill?.softSkills shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.skill?.hardSkills shouldBe listOf("Kotlin")
    }

    @Test
    internal fun language() {
        val person = introduce {
            name = "정석준"
            company = "비바리퍼블리카"
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
        person.name shouldBe "정석준"
        person.company shouldBe "비바리퍼블리카"
        person.skill?.softSkills shouldBe listOf("A passion for problem solving", "Good communication skills")
        person.skill?.hardSkills shouldBe listOf("Kotlin")
        person.language?.languages shouldBe mapOf("Korean" to 5, "English" to 3)
    }
}
