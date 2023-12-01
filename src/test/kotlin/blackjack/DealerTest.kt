package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.CompareResultItem
import blackjack.domain.Dealer
import blackjack.domain.Hand
import blackjack.domain.Player
import blackjack.domain.State
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

data class DealerCompeteTestData(
    val dealerHand: Hand,
    val playerHand: Hand,
    val expected: CompareResultItem,
)

class DealerTest : FunSpec({
    context("Dealer의 상태가 Hit") {
        withData(
            listOf(
                Hand(
                    cards = listOf(
                        Card(CardNumber.ACE, CardShape.DIAMOND),
                        Card(CardNumber.TWO, CardShape.DIAMOND),
                    ),
                ),
                Hand(
                    cards = listOf(
                        Card(CardNumber.ACE, CardShape.DIAMOND),
                        Card(CardNumber.FIVE, CardShape.DIAMOND),
                    ),
                )
            )
        ) { hand ->
            val dealer = Dealer(hand = hand)

            dealer.state shouldBe State.HIT
        }
    }

    context("Dealer의 상태가 Stay") {
        withData(
            listOf(
                Hand(
                    cards = listOf(
                        Card(CardNumber.ACE, CardShape.DIAMOND),
                        Card(CardNumber.SEVEN, CardShape.DIAMOND),
                    ),
                ),
                Hand(
                    cards = listOf(
                        Card(CardNumber.FIVE, CardShape.DIAMOND),
                        Card(CardNumber.SIX, CardShape.DIAMOND),
                        Card(CardNumber.EIGHT, CardShape.DIAMOND),
                    ),
                ),
                Hand(
                    cards = listOf(
                        Card(CardNumber.ACE, CardShape.DIAMOND),
                        Card(CardNumber.JACK, CardShape.DIAMOND),
                        Card(CardNumber.QUEEN, CardShape.DIAMOND),
                    ),
                ),
            )
        ) { hand ->
            val dealer = Dealer(hand = hand)

            dealer.state shouldBe State.STAY
        }
    }

    context("Dealer의 상태가 Blackjack") {
        withData(
            listOf(
                Hand(
                    cards = listOf(
                        Card(CardNumber.ACE, CardShape.DIAMOND),
                        Card(CardNumber.KING, CardShape.DIAMOND),
                    ),
                ),
            )
        ) { hand ->
            val dealer = Dealer(hand = hand)

            dealer.state shouldBe State.BLACKJACK
        }
    }

    context("Dealer의 상태가 Bust") {
        withData(
            listOf(
                Hand(
                    cards = listOf(
                        Card(CardNumber.ACE, CardShape.DIAMOND),
                        Card(CardNumber.JACK, CardShape.DIAMOND),
                        Card(CardNumber.QUEEN, CardShape.DIAMOND),
                        Card(CardNumber.TWO, CardShape.DIAMOND),
                    ),
                ),
            )
        ) { hand ->
            val dealer = Dealer(hand = hand)

            dealer.state shouldBe State.BUST
        }
    }

    context("Dealer는 Player와 승부 결과를 반환한다.") {
        withData(
            listOf(
                DealerCompeteTestData(
                    dealerHand = Hand(
                        cards = listOf(
                            Card(CardNumber.NINE, CardShape.DIAMOND),
                            Card(CardNumber.ACE, CardShape.DIAMOND),
                        ),
                    ),
                    playerHand = Hand(
                        cards = listOf(
                            Card(CardNumber.FIVE, CardShape.SPADE),
                            Card(CardNumber.JACK, CardShape.SPADE),
                        ),
                    ),
                    expected = CompareResultItem.win()
                ),
                DealerCompeteTestData(
                    dealerHand = Hand(
                        cards = listOf(
                            Card(CardNumber.ACE, CardShape.DIAMOND),
                            Card(CardNumber.JACK, CardShape.DIAMOND),
                            Card(CardNumber.QUEEN, CardShape.DIAMOND),
                            Card(CardNumber.TWO, CardShape.DIAMOND),
                        ),
                    ),
                    playerHand = Hand(
                        cards = listOf(
                            Card(CardNumber.ACE, CardShape.HEART),
                            Card(CardNumber.JACK, CardShape.HEART),
                            Card(CardNumber.QUEEN, CardShape.HEART),
                            Card(CardNumber.TWO, CardShape.HEART),
                        ),
                    ),
                    expected = CompareResultItem.lose()
                ),
                DealerCompeteTestData(
                    dealerHand = Hand(
                        cards = listOf(
                            Card(CardNumber.JACK, CardShape.DIAMOND),
                            Card(CardNumber.ACE, CardShape.DIAMOND),
                        ),
                    ),
                    playerHand = Hand(
                        cards = listOf(
                            Card(CardNumber.ACE, CardShape.SPADE),
                            Card(CardNumber.JACK, CardShape.SPADE),
                        ),
                    ),
                    expected = CompareResultItem.draw()
                ),
            )
        ) { (dealerHand, playerHand, expected) ->
            val dealer = Dealer(dealerHand)
            val player = Player("", playerHand)

            println(dealer.state)
            println(player.state)
            dealer.compete(player) shouldBe expected
        }
    }
})
