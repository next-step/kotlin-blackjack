package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.Dealer
import blackjack.domain.Hand
import blackjack.domain.State
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

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
})
