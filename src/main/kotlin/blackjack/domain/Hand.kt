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

    fun getSum(): Int {
        val sum = cards.sumOf {
            it.num.value
        }
        if (containsAce() && sum <= 11) {
            return sum + 10
        }
        return sum
    }

    private fun containsAce(): Boolean {
        return cards.any {
            it.num == CardNumber.ACE
        }
    }
}
