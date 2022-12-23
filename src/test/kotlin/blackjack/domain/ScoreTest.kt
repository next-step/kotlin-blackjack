package blackjack.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ScoreTest : StringSpec({
    "Score의 값이 음수이면 에러 발생" {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            Score(-1)
        }
        exception.message shouldBe "점수값은 음수가 될 수 없습니다."
    }
})
