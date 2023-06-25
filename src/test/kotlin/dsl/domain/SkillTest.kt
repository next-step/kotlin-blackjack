package dsl.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class SkillTest : BehaviorSpec({
    Given("능력설명이 비어있다") {
        val description = "   "

        When("해당 정보로 Soft 능력을 생성하면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { Soft(description) }
            }
        }

        When("해당 정보로 Hard 능력을 생성하면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { Hard(description) }
            }
        }
    }

    Given("능력 설명이 정상적으로 들어온다") {
        val description = "테스트 작성능력"

        When("해당 정보로 Soft능력을 생성하면") {
            Then("에러가 던져지지 않는다") {
                shouldNotThrow<Throwable> { Soft(description) }
            }
        }

        When("해당 정보로 Hard능력을 생성하면") {
            Then("에러가 던져지지 않는다") {
                shouldNotThrow<Throwable> { Hard(description) }
            }
        }
    }
})
