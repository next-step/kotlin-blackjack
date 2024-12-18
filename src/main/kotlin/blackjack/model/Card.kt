package blackjack.model

class Card private constructor(private val suit: Suit, private val rank: Rank) {
    val isAce: Boolean = rank == Rank.ACE

    fun getValue(): Int = rank.value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Card) return false
        return suit == other.suit && rank == other.rank
    }

    override fun hashCode(): Int = 31 * suit.hashCode() + rank.hashCode()

    override fun toString(): String =
        when (rank) {
            Rank.JACK -> "J${suit.description}"
            Rank.QUEEN -> "Q${suit.description}"
            Rank.KING -> "K${suit.description}"
            Rank.ACE -> "A${suit.description}"
            else -> "${rank.value}${suit.description}"
        }

    companion object {
        fun createCard(suit: Suit, rank: Rank): Card {
            return Card(suit, rank)
        }
    }
}
