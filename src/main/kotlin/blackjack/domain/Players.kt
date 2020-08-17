package blackjack.domain

import blackjack.domain.Game.Companion.MAXIMUM_GAME_SCORE

data class Players(private val players: List<Player>) {

    fun findPlayer(nth: Int): Player = players[nth]

    fun size() = players.size

    fun compareWith(dealer: Dealer): Int {
        val dealerState = dealer.getState(REPLY_NO, dealer.totalScore(), dealer.countOfCards())
        if (dealer.isBust(dealerState)) {
            players.forEach { it.winMatch() }
        }

        players.filter {
            MAXIMUM_GAME_SCORE >= it.totalScore() &&
                it.totalScore() >= dealer.totalScore()
        }.forEach { it.winMatch() }

        return players.count { it.isWon() }
    }

    fun statesOfCards(): String {
        return players.joinToString("\n") { "${it}카드: ${it.stateOfCards()}" }
    }

    override fun toString(): String {
        return players.joinToString()
    }
}
