package blackjack.domain

import blackjack.domain.Stat.DRAW
import blackjack.domain.Stat.LOSE
import blackjack.domain.Stat.WIN

class Dealer : Player(NAME) {

    override fun drawable(): Boolean {
        return getPoints() < STOP_POINT
    }

    override fun addCard(card: Card) {
        check(drawable()) { "Dealer can not draw over $STOP_POINT points" }
        super.addCard(card)
    }

    fun compareTo(player: Player): Stat {
        val point = getPoints()
        val playerPoint = player.getPoints()

        return when {
            playerPoint > BLACKJACK_POINT -> WIN
            point > BLACKJACK_POINT -> LOSE
            point > playerPoint -> WIN
            point == playerPoint -> DRAW
            else -> LOSE
        }
    }
    companion object {
        private const val NAME = "딜러"
        const val STOP_POINT = 17
    }
}
