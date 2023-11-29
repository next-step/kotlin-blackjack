package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DefaultGameBlackjackTest : BehaviorSpec({

    val emptyStrategy = InputOutputStrategy(
        { "" },
        {},
        {},
        {}
    )

    val heartCards = CardShuffleStrategy {
        listOf(
            Card(Card.Symbol.HEART, Card.Number.ACE),
            Card(Card.Symbol.HEART, Card.Number.TWO),
            Card(Card.Symbol.HEART, Card.Number.THREE),
            Card(Card.Symbol.HEART, Card.Number.FOUR),
            Card(Card.Symbol.HEART, Card.Number.NINE),
            Card(Card.Symbol.HEART, Card.Number.SEVEN),
        )
    }
    val gameCardDealer = GameCardDealer(heartCards)
    Given("게임 참가자들이 존재하고") {
        val players = listOf(
            GamePlayer("test1", 0),
            GamePlayer("test2", 0)
        )
        When("블랙잭을 시작하면") {
            val blackjack = DefaultGameBlackjack(gameCardDealer, emptyStrategy)
            val playing = blackjack.initialDealing(players)
            Then("딜러가 2장의 카드를 각각 배분하며 딜러도 포함한다.") {
                playing shouldBe GameParticipants(
                    listOf(
                        GameParticipantPlayer(
                            name = "test1",
                            cards = listOf(
                                Card(Card.Symbol.HEART, Card.Number.ACE),
                                Card(Card.Symbol.HEART, Card.Number.TWO)
                            ),
                            betAmount = 0
                        ),
                        GameParticipantPlayer(
                            name = "test2",
                            cards = listOf(
                                Card(Card.Symbol.HEART, Card.Number.THREE),
                                Card(Card.Symbol.HEART, Card.Number.FOUR)
                            ),
                            betAmount = 0
                        )
                    ),
                    GameParticipantDealer(
                        name = "딜러",
                        cards = listOf(
                            Card(Card.Symbol.HEART, Card.Number.NINE),
                            Card(Card.Symbol.HEART, Card.Number.SEVEN)
                        )
                    )
                )
            }
        }
    }
})
