package blackjack.domain

class Card private constructor(
    val suit: Suit,
    val rank: Rank,
) {
    var face: Face = Face.UP
        private set
    val rankValue: Int
        get() = rank.value
    val isFaceUp: Boolean
        get() = face == Face.UP

    fun flip() {
        if (face == Face.UP) {
            face = Face.DOWN
        } else {
            face = Face.UP
        }
    }

    companion object {
        val ALL_CARDS =
            Suit.entries.flatMap { suit ->
                Rank.entries.map { rank ->
                    Card(suit, rank)
                }
            }

        fun of(
            suit: Suit,
            rank: Rank,
        ): Card =
            ALL_CARDS.find { it.suit == suit && it.rank == rank }
                ?: throw IllegalArgumentException("$rank$suit 카드는 존재하지 않습니다.")
    }
}
