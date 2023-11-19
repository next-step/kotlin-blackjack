package blackjack

import blackjack.GameBlackjack.Companion.BLACKJACK_MAX_SCORE

sealed class GameParticipant(
    val name: String,
    val cards: List<Card> = emptyList()
){
    val isBust: Boolean = isBust(cards)

    class Player(
        name: String,
        cards: List<Card> = emptyList()
    ): GameParticipant(name, cards) {
        override fun isNotAllowedDealing(): Boolean = this.isBust || this.isBlackjack()
    }

    class Dealer(
        name: String = NAME,
        cards: List<Card> = emptyList()
    ): GameParticipant(name, cards) {
        override fun isNotAllowedDealing(): Boolean = getScore() > 16

        companion object {
            private const val NAME = "딜러"
        }
    }

    abstract fun isNotAllowedDealing(): Boolean

    fun isBlackjack(): Boolean = getScore(this.cards) == BLACKJACK_MAX_SCORE

    fun receiveCard(card: Card): GameParticipant {
        val updatedCards = mutableListOf(card).apply { this.addAll(cards) }
        return Player(
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

        other as GameParticipant

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
