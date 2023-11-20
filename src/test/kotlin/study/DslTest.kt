package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("박재성")
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
 *
 * */
class DslTest {
    @ValueSource(strings = ["최윤녕", "홍길동"])
    @ParameterizedTest
    fun name(name: String) {
        val person = introduce {
            name(name)
        }
        person.name shouldBe name
    }

    @Test
    fun company() {
        val person = introduce {
            name("최윤녕")
            company("활빈당")
        }
        person.name shouldBe "최윤녕"
        person.company shouldBe "활빈당"
    }

    @Test
    fun skills() {
        val person = introduce {
            name("최윤녕")
            company("활빈당")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "최윤녕"
        person.company shouldBe "활빈당"
        person.skills[0].desc shouldBe "A passion for problem solving"
        person.skills[1].desc shouldBe "Good communication skills"
        person.skills[2].desc shouldBe "Kotlin"
    }

    @Test
    fun languages() {
        val person = introduce {
            name("최윤녕")
            company("활빈당")
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
        person.name shouldBe "최윤녕"
        person.company shouldBe "활빈당"
        person.languages[0].level shouldBe 5
        person.languages[0].lang shouldBe "Korean"
        person.languages[1].level shouldBe 3
        person.languages[1].lang shouldBe "English"
    }
}
fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
