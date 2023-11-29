package blackJack.domain.card

import blackJack.domain.enums.Rank
import blackJack.domain.enums.Suit
import blackJack.error.ErrorMessage

class CardDeck private constructor(val cards: Cards) {

    init {
        require(cards.cardSize == 52) { ErrorMessage.CARD_DECK_SIZE.message }
    }

    fun drawCard(): Card = cards.drawCard()

    companion object {
        const val INIT_CARD_COUNT = 2

        fun createShuffledDeck(): Cards {
            val cardDeck = Suit.values().flatMap { suit ->
                Rank.values().map { rank ->
                    Card(suit, rank)
                }
            }.shuffled()

            return Cards(cardDeck)
        }
    }
}
