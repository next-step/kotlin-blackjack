package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameBlackjackTest : BehaviorSpec({

    val heartCards = CardShuffleStrategy {
        listOf(
            Card(Card.Symbol.HEART, Card.Number.ACE),
            Card(Card.Symbol.HEART, Card.Number.TWO),
            Card(Card.Symbol.HEART, Card.Number.THREE),
            Card(Card.Symbol.HEART, Card.Number.FOUR),
        )
    }
    val gameDealer = GameDealer(heartCards)
    Given("게임 참가자들이 존재하고") {
        val names = "test1,test2"
        When("블랙잭을 시작하면") {
            val blackjack = GameBlackjack(gameDealer)
            val playing = blackjack.initialDealing(names)
            Then("딜러가 2장의 카드를 각각 배분한다.") {
                playing shouldBe GamePlayers(
                    listOf(
                        GamePlayer(
                            name = "test1",
                            cards = listOf(
                                Card(Card.Symbol.HEART, Card.Number.ACE),
                                Card(Card.Symbol.HEART, Card.Number.TWO),
                            )
                        ),
                        GamePlayer(
                            name = "test2",
                            cards = listOf(
                                Card(Card.Symbol.HEART, Card.Number.THREE),
                                Card(Card.Symbol.HEART, Card.Number.FOUR),
                            )
                        )
                    )
                )
            }
        }
    }
})
