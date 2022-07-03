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

    "버스트가 되지 않는다면, ACE의 개수만큼 점수를 증가시킨다." - {
        listOf(
            row(12, 2, 12),
            row(21, 1, 21),
            row(1, 1, 11),
            row(15, 1, 15),
            row(9, 1, 19),
        ).forEach { (currentScore, countOfAce, newScore) ->
            "$currentScore 에서 ACE가 $countOfAce 개면 $newScore 가 된다." {
                val score = Score(value = currentScore)
                score.increaseAceScoreBeforeBust(countOfAce = countOfAce) shouldBe Score(value = newScore)
            }
        }
    }
})
