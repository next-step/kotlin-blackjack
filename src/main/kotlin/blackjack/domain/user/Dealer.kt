package blackjack.domain.user

import blackjack.domain.card.Card

/**
 * 딜러
 * Created by Jaesungchi on 2022.06.15..
 */
class Dealer(initCards: List<Card>) : User(DEALER_NAME, initCards) {

    fun getWinAndLose(users: List<User>): Pair<Int, Int> {
        if (isBust())
            return Pair(0, users.size)
        val winSize = users.filter { isWin(it) }.size
        return Pair(winSize, users.size - winSize)
    }

    fun isOverHitScore(): Boolean {
        return cards.getScore().value >= HIT_SCORE
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val HIT_SCORE = 17
    }
}
