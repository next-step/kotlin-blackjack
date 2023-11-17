package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class GamePlayerTest : BehaviorSpec({

    val cards = listOf(
        Card(Card.Symbol.SPADE, Card.Number.TEN),
        Card(Card.Symbol.SPADE, Card.Number.TWO),
    )
    val cardShuffleStrategy = CardShuffleStrategy {
        listOf(Card(Card.Symbol.SPADE, Card.Number.THREE))
    }
    Given("플레이어가 딜러에게 카드를 추가로 받는다면") {
        val gamePlayer = GamePlayer(
            "test",
            cards,
            PlayerAction.INIT
        )
        val dealer1 = GameDealer(cardShuffleStrategy)
        Then("카드가 한 장 추가되고 상태는 HIT 상태가 된다") {
            val updatedPlayer = gamePlayer.receiveCard(dealer1.deal())
            updatedPlayer shouldBe GamePlayer(
                "test",
                listOf(
                    Card(Card.Symbol.SPADE, Card.Number.THREE),
                    Card(Card.Symbol.SPADE, Card.Number.TEN),
                    Card(Card.Symbol.SPADE, Card.Number.TWO)
                ),
                PlayerAction.HIT
            )
        }

        When("카드의 합이 21을 초과한다면") {
            val burstPlayer = GamePlayer(
                "test",
                listOf(
                    Card(Card.Symbol.SPADE, Card.Number.TWO),
                    Card(Card.Symbol.SPADE, Card.Number.NINE),
                    Card(Card.Symbol.SPADE, Card.Number.KING),
                ),
                PlayerAction.INIT
            )
            val dealer2 = GameDealer(cardShuffleStrategy)
            Then("isBurst 필드가 true로 변경된다.") {
                val updatedPlayer = burstPlayer.receiveCard(dealer2.deal())
                updatedPlayer shouldBe GamePlayer(
                    name = "test",
                    cards = listOf(
                        Card(Card.Symbol.SPADE, Card.Number.THREE),
                        Card(Card.Symbol.SPADE, Card.Number.TWO),
                        Card(Card.Symbol.SPADE, Card.Number.NINE),
                        Card(Card.Symbol.SPADE, Card.Number.KING),
                    ),
                    action = PlayerAction.HIT,
                    isBurst = true
                )
            }
        }
    }
})
