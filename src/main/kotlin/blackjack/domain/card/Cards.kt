package blackjack.domain.card

import blackjack.domain.Blackjack

class Cards(
    cards: List<Card>,
) : AbstractCards(cards.toMutableList()) {
    val score: Int
        get() = CardCalculator.score(this)

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun isBust(): Boolean = score > Blackjack.BLACKJACK_BEST_SCORE

    fun isBlackjack(): Boolean = score == Blackjack.BLACKJACK_BEST_SCORE

    companion object {
        val ALL: Cards = Cards(Card.ALL_CARD_LIST)
    }

    override fun toString(): String {
        return cards.joinToString(", ")
    }
}
