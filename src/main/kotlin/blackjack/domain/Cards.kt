package blackjack.domain

data class Cards(
    val values: List<Card> = emptyList()
) {
    constructor(vararg card: Card) : this(values = card.map { it }.toList())

    fun sumOfScoreWithAceAsOne(): Int {
        return values.sumOf { it.getScore() }
    }
}
