package blackjack.card.deck

import blackjack.card.Card

class BlackJackCardDeck(
    private val cardSets: ArrayDeque<Card>,
) {

    constructor(vararg cardSets: Card) : this(ArrayDeque(cardSets.toList()))

    fun castCard(): Card {
        return cardSets.removeFirstOrNull() ?: throw CardDeckEmptyException()
    }

    class CardDeckEmptyException : RuntimeException("카드 덱이 비어있습니다")
}
