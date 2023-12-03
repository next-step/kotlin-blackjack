package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameResultTest : BehaviorSpec({

    Given("게임의 결과가 있을 때") {
        val gameResult = GameResult(
            dealerName = "딜러",
            playerResults = listOf(
                GameResult.GameResultByPlayer(
                    playerName = "플레이어1",
                    result = GameResult.Result.WIN
                ),
                GameResult.GameResultByPlayer(
                    playerName = "플레이어2",
                    result = GameResult.Result.LOSE
                ),
                GameResult.GameResultByPlayer(
                    playerName = "플레이어3",
                    result = GameResult.Result.WIN
                ),
            )
        )
        When("딜러가 이긴 횟수를 계산하면") {
            val result = gameResult.dealerWinCount

            Then("해당 값을 반환한다.") {
                result shouldBe 1
            }
        }

        When("딜러가 무승부한 횟수를 계산하면") {
            val result = gameResult.dealerDrawCount

            Then("해당 값을 반환한다.") {
                result shouldBe 0
            }
        }

        When("딜러가 진 횟수를 계산하면") {
            val result = gameResult.dealerLoseCount

            Then("해당 값을 반환한다.") {
                result shouldBe 2
            }
        }
    }
})
