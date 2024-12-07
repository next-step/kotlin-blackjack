package blackjack.domain

data class PlayerMutableCards(val cards: MutableList<Card>) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun cardsToString(): String {
        return cards.joinToString(", ") { it.rank.alias + it.suit.alias }
    }

    fun sumValues(): Int {
        var sum = cards.sumOf { card -> card.rank.getNumber().max() }
        var aceCount = cards.count { card -> card.rank == Rank.ACE }

        while (sum > MAX_SUM_CARD_VALUES && aceCount > 0) {
            sum -= Rank.ACE.getNumber().max()
            sum += Rank.ACE.value
            aceCount--
        }

        return sum
    }

    companion object {
        const val MAX_SUM_CARD_VALUES = 21
    }
}
