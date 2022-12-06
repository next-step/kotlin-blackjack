package study

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class DslTest : FunSpec({
    context("introduce 테스트") {
        withData(listOf("길상현", "씨유", "제이슨", "브라운", "포비")) { name ->
            val person = introduce {
                name(name)
            }

            person.name shouldBe name
        }
    }

    context("company 테스트") {
        withData(listOf("네이버", "카카오", "라인", "쿠팡", "우아한형제들")) { company ->
            val person = introduce {
                name("길상현")
                company(company)
            }

            person.company shouldBe company
        }
    }

    context("skills 테스트") {
        val person: Person = introduce {
            name("길상현")
            company("회사")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.name shouldBe "길상현"
        person.company shouldBe "회사"
        person.skills shouldContainExactly listOf(
            SoftSkill("A passion for problem solving"),
            SoftSkill("Good communication skills"),
            HardSkill("Kotlin")
        )
    }

    context("languages 테스트") {
        val person = introduce {
            name("길상현")
            company("회사")
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

        person.name shouldBe "길상현"
        person.company shouldBe "회사"
        person.skills shouldContainExactly listOf(
            SoftSkill("A passion for problem solving"),
            SoftSkill("Good communication skills"),
            HardSkill("Kotlin")
        )

        person.language shouldContainExactly listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
})
