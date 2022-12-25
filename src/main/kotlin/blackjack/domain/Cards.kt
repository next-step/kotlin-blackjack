package blackjack.domain

@JvmInline
value class Cards(val cards: Set<Card>) {

    fun add(card: Card): Cards {
        if (cards.contains(card)) {
            throw IllegalArgumentException("cards contain the card already")
        }

        val addableCards = cards.toMutableSet()
        addableCards.add(card)
        return Cards(addableCards)
    }

    fun sum(): Int {
        val aceCards = cards.filter { it.value == CardValue.ACE }
            .toSet()

        if (aceCards.isEmpty()) {
            return cards.sumOf { it.value.value }
        }

        val sum = cards.minus(aceCards)
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
