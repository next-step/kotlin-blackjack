package blackjack

import blackjack.Card.Number
import blackjack.Card.Symbol
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameParticipantTest : BehaviorSpec({

    val cardShuffleStrategy = CardShuffleStrategy {
        listOf(
            Card(Symbol.SPADE, Number.THREE),
            Card(Symbol.SPADE, Number.ACE)
        )
    }
    Given("플레이어가 딜러에게 카드를 추가로 받는다면") {
        val cards = listOf(
            Card(Symbol.SPADE, Number.TEN),
            Card(Symbol.SPADE, Number.TWO),
        )
        val gamePlayer = GameParticipant.Player(
            "test",
            cards
        )
        val dealer1 = GameDealer(cardShuffleStrategy)
        Then("카드가 한 장 추가된다.") {
            val updatedPlayer = gamePlayer.receiveCard(dealer1.deal())
            updatedPlayer shouldBe GameParticipant.Player(
                "test",
                listOf(
                    Card(Symbol.SPADE, Number.THREE),
                    Card(Symbol.SPADE, Number.TEN),
                    Card(Symbol.SPADE, Number.TWO)
                )
            )
        }

        When("카드의 합이 21을 초과한다면") {
            val bustPlayer = GameParticipant.Player(
                "test",
                listOf(
                    Card(Symbol.SPADE, Number.TWO),
                    Card(Symbol.SPADE, Number.NINE),
                    Card(Symbol.SPADE, Number.KING),
                )
            )
            val bustDealer = GameDealer(cardShuffleStrategy)
            Then("버스트가 된다") {
                val updatedPlayer = bustPlayer.receiveCard(bustDealer.deal())
                updatedPlayer shouldBe GameParticipant.Player(
                    name = "test",
                    cards = listOf(
                        Card(Symbol.SPADE, Number.THREE),
                        Card(Symbol.SPADE, Number.TWO),
                        Card(Symbol.SPADE, Number.NINE),
                        Card(Symbol.SPADE, Number.KING),
                    )
                )
            }
        }
    }

    Given("참가자에는 플레이어와 딜러가 있으며") {
        When("플레이어는 21점과 동일하거나 초과하면") {
            val same = GameParticipant.Player(
                "test",
                listOf(
                    Card(Symbol.HEART, Number.NINE),
                    Card(Symbol.HEART, Number.KING),
                    Card(Symbol.HEART, Number.TWO),
                )
            )
            val exceeded = GameParticipant.Player(
                "test",
                listOf(
                    Card(Symbol.HEART, Number.NINE),
                    Card(Symbol.HEART, Number.KING),
                    Card(Symbol.HEART, Number.JACK),
                )
            )
            Then("카드를 추가 받을 수 없다.") {
                same.isNotAllowedDealing() shouldBe true
                exceeded.isNotAllowedDealing() shouldBe true
            }
        }

        When("딜러는 16점을 초과하면") {
            val exceeded = GameParticipant.Dealer(
                "test",
                listOf(
                    Card(Symbol.HEART, Number.NINE),
                    Card(Symbol.HEART, Number.KING)
                )
            )
            Then("카드를 추가 받을 수 없다.") {
                exceeded.isNotAllowedDealing() shouldBe true
            }
        }
    }
})
