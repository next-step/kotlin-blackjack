package dsl.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class SkillsTest : BehaviorSpec({
    Given("빈 능력 리스트가 있다") {
        val list = emptyList<Skill>()
        When("해당 리스트로 Skills를 생성하면") {
            Then("에러가 던져진다") {
                shouldThrow<IllegalArgumentException> { Skills(list) }
            }
        }
    }

    Given("정상적인 능력 리스트가 있다") {
        val list = listOf(Soft("테스트 작성능력"), Hard("스프링 개발 능력"))
        When("해당 리스트로 Skills를 생성하면") {
            Then("에러가 던져지지 않는다") {
                shouldNotThrow<Throwable> { Skills(list) }
            }
        }
    }
})
