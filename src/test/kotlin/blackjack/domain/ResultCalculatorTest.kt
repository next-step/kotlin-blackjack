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
                    row( // 딜러 Bust
                        Dealer(
                            Hand(
                                mutableListOf(
                                    Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TWO)
                                )
                            )
                        ),
                        Player("yeongun", Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.EIGHT)))),
                        GameResult.WIN
                    ),
                    row( // 플레이어 Bust
                        Dealer(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)))),
                        Player(
                            "yeongun",
                            Hand(
                                mutableListOf(
                                    Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TWO)
                                )
                            )
                        ),
                        GameResult.LOSE
                    ),
                    row(
                        Dealer(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)))),
                        Player("yeongun", Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.EIGHT)))),
                        GameResult.LOSE
                    ),
                    row(
                        Dealer(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)))),
                        Player("yeongun", Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TEN)))),
                        GameResult.WIN
                    ),
                    row(
                        Dealer(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)))),
                        Player("yeongun", Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)))),
                        GameResult.DRAW
                    ),
                ) { dealer, player, expected ->
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
                        Dealer(
                            Hand(
                                mutableListOf(
                                    Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TWO)
                                )
                            )
                        ),
                        Player("yeongun", Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.EIGHT)))),
                        GameResult.LOSE
                    ),
                    row( // 플레이어 Bust
                        Dealer(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)))),
                        Player(
                            "yeongun",
                            Hand(
                                mutableListOf(
                                    Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TWO)
                                )
                            )
                        ),
                        GameResult.WIN
                    ),
                    row(
                        Dealer(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)))),
                        Player("yeongun", Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.EIGHT)))),
                        GameResult.WIN
                    ),
                    row(
                        Dealer(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)))),
                        Player("yeongun", Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TEN)))),
                        GameResult.LOSE
                    ),
                    row(
                        Dealer(Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)))),
                        Player("yeongun", Hand(mutableListOf(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.NINE)))),
                        GameResult.DRAW
                    ),
                ) { dealer, player, expected ->
                    dealer.getResult(player) shouldBe expected
                }
            }
        }
    }
})
