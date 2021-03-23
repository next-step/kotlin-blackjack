package blackjack.domain

import kotlin.math.abs

abstract class GameParticipants(
    val name: String
) {
    val cards: Cards = Cards(arrayListOf())

    init {
        repeat(PLAYER_INIT_CARD) {
            cards.drawCard()
        }
    }

    abstract fun drawCard()
    abstract fun calculateMyCards(): Int
    fun getDistance(): Int{
        return abs(21 - cards.calculateMyCards())
    }

    companion object {
        const val PLAYER_INIT_CARD = 2
    }
}
