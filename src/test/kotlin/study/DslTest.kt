package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * introduce {
 *   name("홍길동")
 *   company("활빈당")
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
    @ValueSource(strings = ["홍길동", "남기현"])
    @ParameterizedTest
    fun name(name: String) {
        val person = introduce {
            name(name)
        }
        person.name shouldBe name
    }

    @ValueSource(strings = ["활빈당", "카카오"])
    @ParameterizedTest
    fun company(name: String) {
        val person = introduce {
            name("홍길동")
            company(name)
        }
        person.company shouldBe name
    }

    @Test
    fun skills() {
        val person = introduce {
            name("홍길동")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.skills[0] shouldBe Skill("A passion for problem solving", SkillLevel.SOFT)
        person.skills[1] shouldBe Skill("Good communication skills", SkillLevel.SOFT)
        person.skills[2] shouldBe Skill("Kotlin", SkillLevel.HARD)
    }

    @Test
    fun languages() {
        val person = introduce {
            name("홍길동")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        person.languages[0] shouldBe Language("Korean", 5)
        person.languages[1] shouldBe Language("English", 3)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
