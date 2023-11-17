package blackjack

import blackjack.GameBlackjack.Companion.BLACKJACK_MAX_SCORE

class GamePlayer(
    val name: String,
    val cards: List<Card> = emptyList(),
    val isBust: Boolean = false,
    val isSoftHand: Boolean = false
) {
    fun receiveCard(card: Card): GamePlayer {
        val updatedCards = mutableListOf(card).apply { this.addAll(cards) }
        return GamePlayer(
            name = this.name,
            cards = updatedCards,
            isBust = isBust(updatedCards),
            isSoftHand = isSoftHand(updatedCards)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GamePlayer

        if (name != other.name) return false
        if (cards != other.cards) return false
        if (isBust != other.isBust) return false
        return isSoftHand == other.isSoftHand
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + cards.hashCode()
        result = 31 * result + isBust.hashCode()
        result = 31 * result + isSoftHand.hashCode()
        return result
    }

    companion object {
        private fun isSoftHand(cards: List<Card>): Boolean {
            if (isNotContainsAce(cards)) return false
            return cards.sumOf { it.number.value } <= BLACKJACK_MAX_SCORE
        }

        private fun isNotContainsAce(cards: List<Card>): Boolean =
            cards.find { it.number == Card.Number.ACE } == null

        private fun isBust(cards: List<Card>): Boolean {
            val sum = cards.sumOf { it.number.value }
            if (isNotContainsAce(cards)) return sum > BLACKJACK_MAX_SCORE
            else if (sum > BLACKJACK_MAX_SCORE) return changeHardHand(sum) > BLACKJACK_MAX_SCORE

            return false
        }

        private fun changeHardHand(score: Int) = score - Card.Number.TEN.value
        fun of(name: String, cards: List<Card>): GamePlayer =
            GamePlayer(
                name = name,
                cards = cards,
                isBust = false,
                isSoftHand = isSoftHand(cards)
            )
    }
}
