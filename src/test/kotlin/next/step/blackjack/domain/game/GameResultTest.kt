package next.step.blackjack.domain.game

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class GameResultTest : DescribeSpec({

    describe("GameResult") {
        context("opposite을 통해 반대되는 결과 제공") {
            data class OppositeExpected(val input: GameResult, val opposite: GameResult)
            withData(
                OppositeExpected(GameResult.TIE, GameResult.TIE),
                OppositeExpected(GameResult.UNDECIDED, GameResult.UNDECIDED),
                OppositeExpected(GameResult.WIN, GameResult.LOSE),
                OppositeExpected(GameResult.LOSE, GameResult.WIN)
            ) { (input, opposite) ->
                input.opposite() shouldBe opposite
            }
        }
        context("UNDECIDED이면 then으로 다음 결과를 제공") {
            withData(
                GameResult.values().toList()
            ) { gameResult ->
                GameResult.UNDECIDED then gameResult shouldBe gameResult
            }
        }
        context("UNDECIDED가 아니면 then을 해도 자신의 결과를 제공") {
            withData(
                GameResult.values().filterNot { it == GameResult.UNDECIDED }
            ) { gameResult ->
                gameResult then GameResult.TIE shouldBe gameResult
            }
        }
    }
})
