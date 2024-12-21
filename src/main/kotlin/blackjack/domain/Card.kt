package blackjack.domain

data class Card(
    val suit: Suit,
    val rank: Rank,
    private var _isFaceUp: Boolean = true,
) {
    val rankValue: Int get() = rank.value
    val isFaceUp: Boolean get() = _isFaceUp

    fun flip() {
        _isFaceUp = !_isFaceUp
    }

    companion object {
        val ALL_CARDS =
            Suit.entries.flatMap { suit ->
                Rank.entries.map { rank ->
                    Card(suit, rank)
                }
            }
    }
}
