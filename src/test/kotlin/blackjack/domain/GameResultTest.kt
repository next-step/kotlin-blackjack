package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class GameResultTest : BehaviorSpec({
    Given("게임 결과가 주어졌을 때") {
        When("reverse 메서드를 호출하면") {
            Then("반대의 결과를 반환한다.") {
                forAll(
                    row(GameResult.WIN, GameResult.LOSE),
                    row(GameResult.LOSE, GameResult.WIN),
                    row(GameResult.DRAW, GameResult.DRAW),
                ) { result, expected ->
                    result.reverse() shouldBe expected
                }
            }
        }
    }
})
