package study

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

/**
 * introduce {
 *   name("김준호")
 *   company("카카오")
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
class DslTest : FunSpec({
    context("DSL로 이름을 넣을수 있다") {
        withData(
            "김준호",
            "홍길동",
        ) { name ->
            val person = introduce {
                name(name)
            }
            person.name shouldBe name
            person.company.shouldBeNull()
        }
    }

    context("DSL로 회사를 넣을수 있다") {
        withData(
            "카카오",
            "네이버",
            "구글",
        ) { company ->
            val person = introduce {
                name("김준호")
                company(company)
            }

            person.name shouldBe "김준호"
            person.company shouldBe company
        }
    }

    context("DSL로 스킬을 넣을수 있다") {
        val person = introduce {
            name("김준호")
            company("카카오")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.name shouldBe "김준호"
        person.company shouldBe "카카오"

        person.skills.soft shouldBe listOf(
            "A passion for problem solving",
            "Good communication skills",
        )

        person.skills.hard shouldBe listOf(
            "Kotlin",
        )
    }

    context("DSL로 언어를 넣을수 있다") {
        val person = introduce {
            name("김준호")
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

        person.name shouldBe "김준호"
        person.company shouldBe "카카오"

        person.skills.soft shouldBe listOf(
            "A passion for problem solving",
            "Good communication skills",
        )

        person.skills.hard shouldBe listOf(
            "Kotlin",
        )

        person.languages shouldBe mapOf(
            "Korean" to 5,
            "English" to 3,
        )
    }
})
