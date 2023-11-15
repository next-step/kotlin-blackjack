package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class GameBlackjackTest : BehaviorSpec({

    Given("GamePlayers가 존재하고") {
        val players = GamePlayers(
            listOf(
                GamePlayer("test1"),
                GamePlayer("test2")
            )
        )
        When("블랙잭을 시작하면") {
            val playing = GameBlackjack.init(players)
            Then("딜러가 2장의 카드를 나눈다.") {
                playing.players.map {
                    it.cards.size shouldBe 2
                }
            }
        }
    }
})
