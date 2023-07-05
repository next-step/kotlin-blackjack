package blackjack.domain

data class Cards(
    val values: List<Card> = emptyList()
) {
    constructor(vararg card: Card) : this(values = card.toList())

    val sumOfScoreWithAceAsOne: Int by lazy {
        values.sumOf { it.getScore() }
    }
}
