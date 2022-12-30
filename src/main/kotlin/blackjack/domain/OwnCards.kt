package blackjack.domain

class OwnCards(private val _cards: MutableSet<Card> = mutableSetOf()) {

    constructor(drawCount: Int) : this() {
        repeat(drawCount) { addCard() }
    }

    val cards: Set<Card>
        get() = _cards

    fun sumCardNumber() = _cards.sumOf {
        it.cardNumberSubValue ?: it.cardNumber.value
    }

    fun addCard(card: Card = Draw.draw()) {
        checkAceValue(card)
        _cards.add(card)
    }

    private fun checkAceValue(card: Card) {
        if ((card.cardNumber == CardNumber.ACE) && (sumCardNumber() + card.cardNumber.value > Draw.DRAW_LIMIT))
            card.cardNumberSubValue = CardNumber.ACE_SUB_VALUE
    }
}
