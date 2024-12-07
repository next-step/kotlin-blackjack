package blackjack.entity

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameResultTest : BehaviorSpec({
    Given("GameResult의 팩토리 메서드가 호출될 때") {
        val player = Player(name = "Pobi", bettingAmount = BettingAmount(1000))

        When("무승부(draw) 결과를 생성하면") {
            val result = GameResult.draw(player)

            Then("수익은 0이어야 한다") {
                result.player shouldBe player
                result.earning shouldBe 0
            }
        }

        When("블랙잭 승리(blackjackWin) 결과를 생성하면") {
            val result = GameResult.blackjackWin(player, player.bettingAmount)

            Then("수익은 베팅 금액의 1.5배가 되어야 한다") {
                result.player shouldBe player
                result.earning shouldBe (1000 * 1.5).toInt()
            }
        }

        When("승리(win) 결과를 생성하면") {
            val result = GameResult.win(player, player.bettingAmount)

            Then("수익은 베팅 금액과 동일해야 한다") {
                result.player shouldBe player
                result.earning shouldBe 1000
            }
        }

        When("패배(lose) 결과를 생성하면") {
            val result = GameResult.lose(player, player.bettingAmount)

            Then("수익은 베팅 금액의 음수(-amount)가 되어야 한다") {
                result.player shouldBe player
                result.earning shouldBe -1000
            }
        }
    }
})
