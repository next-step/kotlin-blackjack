package blackjack.domain

class OwnCards(private val _cards: MutableSet<Card> = buildSet { repeat(Draw.FIRST_DRAW_COUNT) { add(Card()) } }.toMutableSet()) {

    val cards: Set<Card>
        get() = _cards

    fun sumCardNumber() = _cards.sumOf { if (it.cardNumber.isSmall) it.cardNumber.value % 10 else it.cardNumber.value }

    fun addCard(drawYn: Boolean) {
        if (drawYn) _cards.add(makeCard())
    }

    private fun makeCard(): Card {
        var card: Card
        do {
            card = Card()
        } while (_cards.contains(card))
        if ((card.cardNumber == CardNumber.ACE) && (sumCardNumber() + card.cardNumber.value > Draw.DRAW_LIMIT))
            card.cardNumber.isSmall = true
        return card
    }
}
