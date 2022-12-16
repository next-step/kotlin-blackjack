package blackjack.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ScoreTest : StringSpec({
    "Score의 값이 음수이면 에러 발생" {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            Score(-1)
        }
        exception.message shouldBe "점수값은 0보다 커야합니다."
    }

    "Score의 값이 0이면 에러 발생" {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            Score(0)
        }
        exception.message shouldBe "점수값은 0보다 커야합니다."
    }
})
