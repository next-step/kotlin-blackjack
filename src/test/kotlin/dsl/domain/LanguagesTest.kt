package dsl.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class LanguagesTest : BehaviorSpec({
    Given("빈 언어 리스트가 있다") {
        val list = emptyList<Language>()
        When("해당 리스트로 Languages를 생성하면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { Languages(list) }
            }
        }
    }

    Given("정상적인 능력 리스트가 있다") {
        val list = listOf(Language("korean", 1))
        When("해당 리스트로 Languages를 생성하면") {
            Then("에러가 던져지지 않는다") {
                shouldNotThrow<Throwable> { Languages(list) }
            }
        }
    }
})
