package blackjack.domain

data class Hand(
    val cards: List<Card> = listOf()
) {
    fun init(deck: Deck): Hand {
        return copy(cards = listOf(deck.hit(), deck.hit()))
    }

    fun hit(deck: Deck): Hand {
        val newCard = deck.hit()
        val newCards = cards + newCard
        return copy(cards = newCards)
    }

    fun canHit(): Boolean {
        return getSum() < BLACKJACK_NUMBER
    }

    fun getSum(): Int {
        val sum = cards.sumOf {
            it.num.value
        }
        if (containsAce() && sum <= ACE_NUMBER) {
            return sum + ACE_ADDING_NUMBER
        }
        return sum
    }

    private fun containsAce(): Boolean {
        return cards.any {
            it.num == CardNumber.ACE
        }
    }

    companion object {
        private const val BLACKJACK_NUMBER = 21
        private const val ACE_NUMBER = 11
        private const val ACE_ADDING_NUMBER = 10
    }
}
