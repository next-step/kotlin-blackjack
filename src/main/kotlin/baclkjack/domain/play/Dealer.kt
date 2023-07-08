package baclkjack.domain.play

import baclkjack.domain.card.Cards

class Dealer(override val name: String = "딜러", override val cards: Cards = Cards()) : User {

    override fun isDraw(): Boolean = score() <= DEFAULT_SCORE

    fun result(players: List<Player>): Int = players.sumOf {
        it.result(this)
    } * DEALER_EARNING_RATE

    companion object {
        const val DEFAULT_SCORE = 16
        const val DEALER_EARNING_RATE = -1
    }
}
