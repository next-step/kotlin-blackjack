package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ResultCalculatorTest : BehaviorSpec({
    Given("딜러가 주어지면") {
        When("플레이어는") {
            Then("게임의 결과를 반환한다.") {
                forAll(
                    row(
                        // 딜러 Bust
                        listOf(Card(CardSuit.CLUB, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TWO), Card(CardSuit.CLUB, CardNumber.TEN)),
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.EIGHT)),
                        GameResult.WIN
                    ),
                    row(
                        // 플레이어 Bust
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)),
                        listOf(Card(CardSuit.CLUB, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TWO)),
                        GameResult.LOSE
                    ),
                    row(
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)),
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.EIGHT)),
                        GameResult.LOSE
                    ),
                    row(
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)),
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TEN)),
                        GameResult.WIN
                    ),
                    row(
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)),
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)),
                        GameResult.DRAW
                    ),
                ) { dealerCards, playerCards, expected ->
                    val dealer = Dealer(FixedDeck())
                    dealerCards.forEach { dealer.hit(it) }

                    val player = Player("yeongun")
                    playerCards.forEach { player.hit(it) }

                    player.getResult(dealer) shouldBe expected
                }
            }
        }
    }

    Given("플레어이가 주어지면") {
        When("딜러는") {
            Then("게임의 결과를 반환한다.") {
                forAll(
                    row( // 딜러 Bust
                        listOf(Card(CardSuit.CLUB, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TWO), Card(CardSuit.CLUB, CardNumber.TEN)),
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.EIGHT)),
                        GameResult.LOSE
                    ),
                    row( // 플레이어 Bust
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)),
                        listOf(Card(CardSuit.CLUB, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.TWO)),
                        GameResult.WIN
                    ),
                    row(
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)),
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.EIGHT)),
                        GameResult.WIN
                    ),
                    row(
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)),
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TEN)),
                        GameResult.LOSE
                    ),
                    row(
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)),
                        listOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)),
                        GameResult.DRAW
                    ),
                ) { dealerCards, playerCards, expected ->
                    val dealer = Dealer(FixedDeck())
                    dealerCards.forEach { dealer.hit(it) }

                    val player = Player("yeongun")
                    playerCards.forEach { player.hit(it) }

                    dealer.getResult(player) shouldBe expected
                }
            }
        }
    }
})
