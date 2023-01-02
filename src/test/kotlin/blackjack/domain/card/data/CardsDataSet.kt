package blackjack.domain.card.data

import blackjack.domain.Blackjack
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards

class CardsDataSet {
    companion object {
        private val twoCardsScoreRange: IntRange = (2..Blackjack.BLACKJACK_BEST_SCORE)

        fun testDataWithTwoCards(): Cards =
            testDataWithTwoCards(twoCardsScoreRange.random())

        fun testDataWithTwoCards(score: Int): Cards {
            require(score in twoCardsScoreRange) {
                "score [$score] should be in $twoCardsScoreRange"
            }

            val lowerBound = score / 2
            val randomCardNumber = (lowerBound until score).intersect(CardNumber.INT_RANGE).random()

            return Cards(
                listOf(
                    cardOf(randomCardNumber),
                    cardOf(score - randomCardNumber)
                )
            )
        }

        fun testData(vararg cardNumbers: CardNumber): Cards {
            return Cards(
                cardNumbers.map { Card(it, CardShape.values().random()) }
            )
        }
    }
}

fun cardOf(cardNumber: Int): Card =
    Card(cardNumberOf(cardNumber), CardShape.values().random())

fun cardNumberOf(cardNumber: Int): CardNumber {
    require(cardNumber in CardNumber.INT_RANGE) {
        "cardNumber [$cardNumber] should be in range ${CardNumber.INT_RANGE}"
    }

    return CardNumber.values().first { it.number == cardNumber }
}
