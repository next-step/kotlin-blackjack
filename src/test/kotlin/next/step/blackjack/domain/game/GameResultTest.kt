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
    }
})
