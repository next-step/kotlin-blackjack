package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class GamePlayerTest : BehaviorSpec({

    Given("플레이어가 딜러에게 카드를 받는다면") {
        val gamePlayer = GamePlayer(
            "test",
            listOf(
                Card(Card.Symbol.SPADE, Card.Number.TEN),
                Card(Card.Symbol.SPADE, Card.Number.TWO),
            ),
            PlayerAction.INIT
        )
        Then("카드가 한 장 추가된다.") {
            val updatedPlayer = gamePlayer.receiveCard(GameDealer.deal())
            updatedPlayer.cards.size shouldBe 3
        }

        val burstPlayer = GamePlayer(
            "test",
            listOf(
                Card(Card.Symbol.SPADE, Card.Number.TWO),
                Card(Card.Symbol.SPADE, Card.Number.NINE),
                Card(Card.Symbol.SPADE, Card.Number.KING),
            ),
            PlayerAction.INIT
        )
        When("카드의 합이 21을 초과한다면") {
            Then("isBurst 필드가 true로 변경된다.") {
                val updatedPlayer = burstPlayer.receiveCard(GameDealer.deal())
                updatedPlayer.isBurst shouldBe true
            }
        }
    }
})
