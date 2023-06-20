package study

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import study.dsl.introduce

/**
 * introduce {
 *   name("남현우")
 *   company("카카오")
 *   skills {
 *       soft("A passion for problem solving")
 *       soft("Good communication skills")
 *       hard("Kotlin")
 *   }
 *   languages {
 *       "Korean" level 5
 *       "English" level 3
 *   }
}*/
class PersonDslTest : StringSpec({
    "자기 소개" {
        table(
            headers("이름"),
            row("남현우"),
            row("tris")
        ).forAll {
            val person: Person = introduce {
                name(it)
            }
            person.name shouldBe it
            person.company.shouldBeNull()
        }
    }

    "company" {
        val person = introduce {
            name("남현우")
            company("kakao")
        }

        person.company shouldBe "kakao"
    }

    "skills" {
        val person = introduce {
            name("남현우")
            company("kakao")
            skills {
                soft("소통")
                soft("열망")
                hard("kotlin")
                hard("java")
            }
        }

        person.name shouldBe "남현우"
        person.company shouldBe "kakao"
        person.skills shouldContainExactlyInAnyOrder listOf(
            SoftSkill("소통"),
            SoftSkill("열망"),
            HardSkill("kotlin"),
            HardSkill("java")
        )
    }

    "languages" {
        val person = introduce {
            name("남현우")
            company("kakao")
            skills {
                soft("소통")
                soft("열망")
                hard("kotlin")
                hard("java")
            }
            languages {
                "한국어" level 2
                "영어" level 1
            }
        }

        person.name shouldBe "남현우"
        person.company shouldBe "kakao"
        person.skills shouldContainExactlyInAnyOrder listOf(
            SoftSkill("소통"),
            SoftSkill("열망"),
            HardSkill("kotlin"),
            HardSkill("java")
        )
        person.languages shouldBe mapOf("한국어" to 2, "영어" to 1)
    }
})
