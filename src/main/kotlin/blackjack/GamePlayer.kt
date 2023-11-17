package blackjack

class GamePlayer(
    val name: String,
    val cards: List<Card> = emptyList(),
    val isBust: Boolean = false
) {
    fun receiveCard(card: Card): GamePlayer {
        val updatedCards = mutableListOf(card).apply { this.addAll(cards) }
        return GamePlayer(
            name = this.name,
            cards = updatedCards,
            isBust = isBurst(updatedCards)
        )
    }

    private fun isBurst(cards: List<Card>): Boolean =
        cards.sumOf { it.number.value } > DefaultGameBlackjack.BLACKJACK_MAX_SCORE

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GamePlayer

        if (name != other.name) return false
        if (cards != other.cards) return false
        return isBust == other.isBust
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + cards.hashCode()
        result = 31 * result + isBust.hashCode()
        return result
    }
}
