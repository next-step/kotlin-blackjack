package study

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import study.languages.Language
import study.languages.Languages
import study.person.Person
import study.person.PersonBuilder
import study.skills.HardSkill
import study.skills.Skills
import study.skills.SoftSkill

/**
 * introduce {
 *   name("이정수")
 *   company("회사")
 *   skills {
 *     soft("A passion for problem solving")
 *     soft("Good communication skills")
 *     hard("Kotlin")
 *   }
 *   languages {
 *     "Korean" level 5
 *     "English" level 2
 *   }
 * }
 *
 */

class DslTest {
    @ValueSource(strings = ["홍길동", "이정수"])
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
            name("홍길동")
            company("활빈당")
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
    }

    @Test
    fun skills() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.skills shouldBe (
            Skills(
                listOf(
                    SoftSkill("A passion for problem solving"),
                    SoftSkill("Good communication skills"),
                    HardSkill("Kotlin")
                )
            )
            )
    }

    @Test
    fun languages() {
        val person = introduce {
            name("홍길동")
            company("활빈당")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 2
            }
        }
        person.name shouldBe "홍길동"
        person.company shouldBe "활빈당"
        person.skills shouldBe (
            Skills(
                listOf(
                    SoftSkill("A passion for problem solving"),
                    SoftSkill("Good communication skills"),
                    HardSkill("Kotlin")
                )
            )
            )
        person.languages shouldBe (
            Languages(
                listOf(
                    Language("Korean", 5),
                    Language("English", 2)
                )
            )
            )
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
