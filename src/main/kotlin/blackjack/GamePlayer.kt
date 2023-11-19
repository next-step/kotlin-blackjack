package blackjack

import blackjack.GameBlackjack.Companion.BLACKJACK_MAX_SCORE

class GamePlayer(
    val name: String,
    val cards: List<Card> = emptyList()
) {
    val isBust: Boolean = isBust(cards)

    fun isBlackjack(): Boolean = getScore(this.cards) == BLACKJACK_MAX_SCORE

    fun receiveCard(card: Card): GamePlayer {
        val updatedCards = mutableListOf(card).apply { this.addAll(cards) }
        return GamePlayer(
            name = this.name,
            cards = updatedCards
        )
    }

    fun getScore(cards: List<Card> = this.cards): Int = if (isContainsAce(cards)) getSoftScore(cards) else getHardScore(cards)

    private fun isContainsAce(cards: List<Card>): Boolean =
        cards.find { it.number == Card.Number.ACE } != null

    private fun isBust(cards: List<Card>): Boolean =
        getScore(cards) > BLACKJACK_MAX_SCORE

    private fun getSoftScore(cards: List<Card>): Int {
        val sum = getHardScore(cards)
        return if (sum > BLACKJACK_MAX_SCORE) sum - Card.Number.TEN.value else sum
    }

    private fun getHardScore(cards: List<Card>): Int = cards.sumOf { it.number.value }
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

    companion object {
        fun of(name: String, cards: List<Card>): GamePlayer =
            GamePlayer(
                name = name,
                cards = cards
            )
    }
}
