package blackjack.domain.participant.state

import blackjack.domain.participant.GameResult
import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class ScoreTest : FreeSpec({

    "점수 비교를 통해 승패를 알 수 있다." - {
        listOf(
            row(1, 2, GameResult.LOSE),
            row(3, 2, GameResult.WIN),
            row(21, 20, GameResult.WIN),
            row(21, 2, GameResult.WIN),
            row(2, 2, GameResult.DRAW),
            row(21, 21, GameResult.DRAW),
        ).forEach { (myScore, otherScore, result) ->
            "$myScore vs $otherScore = $result" {
                Score(myScore).compareGameResult(Score(otherScore)) shouldBe result
            }
        }
    }
})
