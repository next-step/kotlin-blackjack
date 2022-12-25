package blackjack.domain

class Cards(cards: Set<Card> = emptySet()) {
    private val _cards: LinkedHashSet<Card> = cards.toMutableSet() as LinkedHashSet<Card>
    val cards: Set<Card>
        get() = _cards.toSet()

    fun add(card: Card) {
        if (_cards.contains(card)) {
            throw IllegalArgumentException("cards contain the card already")
        }

        _cards.add(card)
    }

    fun sum(): Int {
        val aceCards = _cards.filter { it.value == CardValue.ACE }
            .toSet()

        if (aceCards.isEmpty()) {
            return _cards.sumOf { it.value.value }
        }

        val sum = _cards.minus(aceCards)
            .sumOf { it.value.value }

        return addAceValue(sum, aceCards)
    }
    private tailrec fun addAceValue(sum: Int, aceCards: Set<Card>): Int {
        if (aceCards.isEmpty()) {
            return sum
        }

        val aceCard = aceCards.first()
        val sumIncludingAceValue = sum + CardValue.ACE.value

        return if (canAddAceBonusValue(sum)) {
            addAceValue(sumIncludingAceValue + CardValue.ACE_BONUS_VALUE, aceCards.minus(aceCard))
        } else {
            addAceValue(sumIncludingAceValue, aceCards.minus(aceCard))
        }
    }
    private fun canAddAceBonusValue(sum: Int) = sum <= 10
}
