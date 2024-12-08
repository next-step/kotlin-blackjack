package blackjack.domain

data class Card(
    val suit: Suit,
    val rank: Rank,
    private var _face: Face = Face.UP,
) {
    val face: Face
        get() = _face
    val rankValue: Int
        get() = rank.value
    val isFaceUp: Boolean
        get() = _face == Face.UP

    fun flip() {
        if (_face == Face.UP) {
            _face = Face.DOWN
            return
        }
        _face = Face.UP
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
