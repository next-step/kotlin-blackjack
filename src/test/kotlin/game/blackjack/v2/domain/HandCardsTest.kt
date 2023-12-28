package game.blackjack.v2.domain

import game.blackjack.v2.domain.card.Card
import game.blackjack.v2.domain.card.CardNumber
import game.blackjack.v2.domain.card.CardShape
import game.blackjack.v2.domain.participant.HandCards
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class HandCardsTest : BehaviorSpec({

    Given("ACE 카드를 뽑았을 때") {

        When("현재 카드 패의 총 점수가 11점 이상일 경우") {
            Then("ACE 카드는 1점으로 계산한다.") {
                forAll(
                    row(Card(CardNumber.NINE, CardShape.SPADE), Card(CardNumber.TWO, CardShape.SPADE)),
                    row(Card(CardNumber.TEN, CardShape.SPADE), Card(CardNumber.TWO, CardShape.SPADE)),
                    row(Card(CardNumber.JACK, CardShape.SPADE), Card(CardNumber.TWO, CardShape.SPADE)),
                    row(Card(CardNumber.QUEEN, CardShape.SPADE), Card(CardNumber.TWO, CardShape.SPADE)),
                    row(Card(CardNumber.KING, CardShape.SPADE), Card(CardNumber.TWO, CardShape.SPADE)),
                    row(Card(CardNumber.KING, CardShape.SPADE), Card(CardNumber.KING, CardShape.HEART)),
                ) { card1: Card, card2: Card ->
                    val handCards = HandCards()
                    handCards.addAll(listOf(card1, card2))
                    val originScore = handCards.getCurrentScore()

                    handCards.add(Card(CardNumber.ACE, CardShape.SPADE))

                    handCards.getCurrentScore() shouldBe originScore + 1
                }
            }
        }

        When("현재 카드 패의 총 점수가 10점 이하일 경우") {
            Then("ACE 카드는 11점으로 계산한다.") {
                forAll(
                    row(Card(CardNumber.TWO, CardShape.SPADE), Card(CardNumber.EIGHT, CardShape.SPADE)),
                    row(Card(CardNumber.TWO, CardShape.SPADE), Card(CardNumber.TWO, CardShape.HEART)),
                    row(Card(CardNumber.TWO, CardShape.SPADE), Card(CardNumber.THREE, CardShape.SPADE)),
                    row(Card(CardNumber.TWO, CardShape.SPADE), Card(CardNumber.FOUR, CardShape.SPADE)),
                    row(Card(CardNumber.THREE, CardShape.SPADE), Card(CardNumber.SEVEN, CardShape.SPADE)),
                ) { card1: Card, card2: Card ->
                    val handCards = HandCards()
                    handCards.addAll(listOf(card1, card2))
                    val originScore = handCards.getCurrentScore()

                    handCards.add(Card(CardNumber.ACE, CardShape.SPADE))

                    handCards.getCurrentScore() shouldBe originScore + 11
                }
            }
        }
    }

    Given("현재 카드 패에 10점 카드와 ACE 카드(11점)가 있어서 총 점수가 21점일 때") {
        When("10점 카드를 추가로 뽑아 Bust가 발생한 경우") {
            Then("ACE 카드는 1점으로 되어 카드패의 총 점수는 21점으로 계산한다.") {
                forAll(
                    row(
                        listOf(Card(CardNumber.ACE, CardShape.SPADE), Card(CardNumber.TEN, CardShape.SPADE)),
                        Card(CardNumber.TEN, CardShape.SPADE)
                    ),
                    row(
                        listOf(Card(CardNumber.ACE, CardShape.SPADE), Card(CardNumber.JACK, CardShape.SPADE)),
                        Card(CardNumber.TEN, CardShape.SPADE)
                    ),
                    row(
                        listOf(Card(CardNumber.ACE, CardShape.SPADE), Card(CardNumber.QUEEN, CardShape.SPADE)),
                        Card(CardNumber.TEN, CardShape.SPADE)
                    ),
                    row(
                        listOf(Card(CardNumber.ACE, CardShape.SPADE), Card(CardNumber.KING, CardShape.SPADE)),
                        Card(CardNumber.TEN, CardShape.SPADE)
                    ),
                    row(
                        listOf(
                            Card(CardNumber.ACE, CardShape.SPADE),
                            Card(CardNumber.FOUR, CardShape.SPADE),
                            Card(CardNumber.SIX, CardShape.SPADE)
                        ), Card(CardNumber.JACK, CardShape.SPADE)
                    ),
                    row(
                        listOf(
                            Card(CardNumber.ACE, CardShape.SPADE),
                            Card(CardNumber.TWO, CardShape.SPADE),
                            Card(CardNumber.EIGHT, CardShape.SPADE)
                        ), Card(CardNumber.QUEEN, CardShape.SPADE)
                    ),
                    row(
                        listOf(
                            Card(CardNumber.ACE, CardShape.SPADE),
                            Card(CardNumber.TWO, CardShape.SPADE),
                            Card(CardNumber.THREE, CardShape.SPADE),
                            Card(CardNumber.FIVE, CardShape.SPADE)
                        ), Card(CardNumber.KING, CardShape.SPADE)
                    ),
                ) { cards: List<Card>, drawCard: Card ->
                    val handCards = HandCards()
                    handCards.addAll(cards)

                    handCards.add(drawCard)

                    handCards.getCurrentScore() shouldBe 21
                }
            }
        }
    }
})
