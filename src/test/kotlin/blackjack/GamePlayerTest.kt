package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GamePlayerTest : BehaviorSpec({

    val cardShuffleStrategy = CardShuffleStrategy {
        listOf(
            Card(Card.Symbol.SPADE, Card.Number.THREE),
            Card(Card.Symbol.SPADE, Card.Number.ACE)
        )
    }
    Given("플레이어가 딜러에게 카드를 추가로 받는다면") {
        val cards = listOf(
            Card(Card.Symbol.SPADE, Card.Number.TEN),
            Card(Card.Symbol.SPADE, Card.Number.TWO),
        )
        val gamePlayer = GamePlayer(
            "test",
            cards
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
                )
            )
        }

        When("카드의 합이 21을 초과한다면") {
            val bustPlayer = GamePlayer(
                "test",
                listOf(
                    Card(Card.Symbol.SPADE, Card.Number.TWO),
                    Card(Card.Symbol.SPADE, Card.Number.NINE),
                    Card(Card.Symbol.SPADE, Card.Number.KING),
                )
            )
            val bustDealer = GameDealer(cardShuffleStrategy)
            Then("버스트가 되고 하드핸드가 된다.") {
                val updatedPlayer = bustPlayer.receiveCard(bustDealer.deal())
                updatedPlayer shouldBe GamePlayer(
                    name = "test",
                    cards = listOf(
                        Card(Card.Symbol.SPADE, Card.Number.THREE),
                        Card(Card.Symbol.SPADE, Card.Number.TWO),
                        Card(Card.Symbol.SPADE, Card.Number.NINE),
                        Card(Card.Symbol.SPADE, Card.Number.KING),
                    )
                )
            }
        }
    }
})
