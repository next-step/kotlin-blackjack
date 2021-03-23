package blackjack.domain

import blackjack.domain.Cards.Companion.WINNING_NUMBER
import kotlin.math.abs

abstract class GameParticipants(
    val name: String,
    val cards: Cards = Cards(arrayListOf())
) {

    init {
        repeat(PLAYER_INIT_CARD) {
            cards.drawCard()
        }
    }

    abstract fun drawCard()
    abstract fun calculateMyCards(): Int
    fun getDistance(): Int {
        return abs(WINNING_NUMBER - cards.calculateMyCards())
    }

    fun isWinner(winner: List<GameParticipants>): String {
        if (winner.contains(this)) {
            return "승"
        }
        return "패"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GameParticipants) return false

        if (name != other.name) return false
        if (cards != other.cards) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + cards.hashCode()
        return result
    }

    companion object {
        const val PLAYER_INIT_CARD = 2
    }
}
