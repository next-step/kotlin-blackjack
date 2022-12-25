package blackjack.domain

@JvmInline
value class Cards(val cards: Set<Card>) {
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

        return if (canAddAceBonusValue(sum)) {
            addAceValue(sum + CardValue.ACE.value + CardValue.ACE_BONUS_VALUE, aceCards.minus(aceCard))
        } else {
            addAceValue(sum + CardValue.ACE.value, aceCards.minus(aceCard))
        }
    }

    private fun canAddAceBonusValue(sum: Int) = sum <= 10
}
