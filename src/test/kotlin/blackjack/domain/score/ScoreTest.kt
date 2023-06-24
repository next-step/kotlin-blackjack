package blackjack.domain.score

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class ScoreTest : StringSpec({
    "점수가 음수인 경우" {
        shouldThrow<IllegalArgumentException> {
            Score(-1)
        }.shouldHaveMessage("점수는 음수일 수 없습니다. score: -1")
    }
    "버스트 인 경우" {
        val score = Score(22)
        score.isBust() shouldBe true
    }
    "블랙잭 인 경우" {
        val score = Score(21)
        score.isBlackJack() shouldBe true
    }
    "점수 비교" {
        forAll(
            row((Score(1)), Score(2)),
            row((Score(2)), Score(3)),
        ) { left, right ->
            val result = left < right
            result shouldBe true
        }
    }
})
