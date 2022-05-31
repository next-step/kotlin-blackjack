package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ResumeTest : StringSpec({

    "내 이름을 포함한 이력서를 만든다" {
        val expected = "김해중"
        val resume: Resume = introduce {
            name(expected)
        }
        resume.name shouldBe expected
    }
})
