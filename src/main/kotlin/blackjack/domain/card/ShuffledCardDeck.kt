package blackjack.domain.card

import blackjack.domain.ExceptionTypes.EMPTY_SHUFFLED_CARD_DECK

class ShuffledCardDeck(shuffledCards: List<Card>) {
    private val shuffledCards: Iterator<Card> = shuffledCards.iterator()

    fun getNextCard(): Card {
        require(shuffledCards.hasNext()) { EMPTY_SHUFFLED_CARD_DECK }
        return shuffledCards.next()
    }
}
