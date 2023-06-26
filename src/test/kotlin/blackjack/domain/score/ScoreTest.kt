package blackjack.domain.score

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class ScoreTest : StringSpec({
    "점수가 음수인 경우" {
        shouldThrow<IllegalArgumentException> {
            Score(-1)
        }.shouldHaveMessage("점수는 음수일 수 없습니다. score: -1")
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
    "상태 얻기" {
        table(
            headers("점수", "점수 상태"),
            row(Score(1), ScoreState.NORMAL),
            row(Score(20), ScoreState.NORMAL),
            row(Score(21), ScoreState.BLACK_JACK),
            row(Score(22), ScoreState.BUST),
        ).forAll { score, scoreState ->
            score.getState() shouldBe scoreState
        }
    }
})
