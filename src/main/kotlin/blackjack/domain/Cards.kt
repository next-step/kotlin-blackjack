package blackjack.domain

class Cards(
    values: List<Card> = emptyList(),
) {
    var values: List<Card> = values
        private set

    constructor(vararg card: Card) : this(values = card.toMutableList())

    val sumOfScoreWithAceAsOne: Int
        get() = values.sumOf { it.getScore() }

    val numberOfAce: Int
        get() = values.count { it.rank == Rank.ACE }

    fun add(card: Card) {
        values = values + card
    }

    fun addAll(cards: List<Card>) {
        values = values + cards
    }
}
