package blackjack.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ResultCalculatorTest : BehaviorSpec({
    Given("딜러와 플레이어가 주어지면") {
        When("결과 계산기는") {
            Then("플레이어의 결과를 반환한다.") {
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
                    player.calculateResult(dealer) shouldBe expected
                }
            }
        }
    }
})
