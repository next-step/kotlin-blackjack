package study

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class DslTest : FunSpec({
    context("introduce test") {
        withData(listOf("김다희", "가나다", "마바사", "라마바", "파카타")) { name ->
            val person = introduce {
                name(name)
            }

            person.name shouldBe name
        }
    }

    context("company test") {
        withData(listOf("네이버", "카카오", "라인", "쿠팡", "우아한형제들")) { company ->
            val person = introduce {
                name("김다희")
                company(company)
            }

            person.company shouldBe company
        }
    }

    context("skills test") {
        val person: Person = introduce {
            name("김다희")
            company("좋은 회사")
            skills {
                soft("Very Good communication skills")
                hard("Spring")
            }
        }

        person.name shouldBe "김다희"
        person.company shouldBe "좋은 회사"
        person.skills shouldContainExactly listOf(
            SoftSkill("Very Good communication skills"),
            HardSkill("Spring")
        )
    }

    context("languages test") {
        val person = introduce {
            name("김다희")
            company("좋은 회사")
            skills {
                soft("Very Good communication skills")
                hard("Spring")
            }
            languages {
                "Korean" level 10
                "English" level 10
            }
        }

        person.name shouldBe "김다희"
        person.company shouldBe "좋은 회사"
        person.skills shouldContainExactly listOf(
            SoftSkill("Very Good communication skills"),
            HardSkill("Spring")
        )

        person.language shouldContainExactly listOf(
            Language("Korean", 10),
            Language("English", 10)
        )
    }
})
