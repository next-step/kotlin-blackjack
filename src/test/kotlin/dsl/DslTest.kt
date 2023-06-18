package dsl

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DslTest : FunSpec({

    test("kotlin dsl을 이용해 자기소개 정보를 작성할 수 있다.") {
        val actual = introduce {
            name("이한솔")
            company("마이다스인")
            skills {
                soft("Java")
                soft("javascript")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        actual.name shouldBe "이한솔"
        actual.company shouldBe "마이다스인"
        actual.skills?.contains(SoftSkill("Java")) shouldBe true
        actual.skills?.contains(HardSkill("Kotlin")) shouldBe true
        actual.languages?.contains(Language(value = "Korean", level = 5)) shouldBe true
    }
})
