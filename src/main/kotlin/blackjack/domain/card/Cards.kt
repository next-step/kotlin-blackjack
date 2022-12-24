package blackjack.domain.card

class Cards(
    cards: List<Card>,
) : AbstractCards(cards.toMutableList()) {
    fun addCard(card: Card) {
        cards.add(card)
    }

    fun score(): Int = CardCalculator.score(this)

    companion object {
        val ALL: Cards = Cards(Card.ALL_CARD_LIST)
    }

    override fun toString(): String {
        return cards.joinToString(", ")
    }
}
