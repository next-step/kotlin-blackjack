package blackjack.domain.card

import blackjack.domain.Blackjack
import blackjack.domain.player.Player

class CardsDataSet {
    companion object {
        fun testDataWithTwoCards(): Cards {
            val cardVendor = CardVendor()

            val size = (Player.INIT_CARD_COUNT..Player.INIT_CARD_COUNT + 2).random()

            return Cards((1..size).map { cardVendor.drawCard() })
        }

        fun testDataWithTwoCards(score: Int): Cards {
            require(score in (2..Blackjack.BLACKJACK_BEST_SCORE)) {
                "score [$score] should be in ${(2..Blackjack.BLACKJACK_BEST_SCORE)}"
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
