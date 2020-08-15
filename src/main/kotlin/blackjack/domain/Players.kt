package blackjack.domain

import blackjack.domain.Game.Companion.MAXIMUM_GAME_SCORE

data class Players(private val players: List<Player>) {

    fun setUpWithCards(dealer: Dealer = Dealer(Deck())) = players.forEach { it.setUpWithCards(dealer) }

    fun findPlayer(nth: Int): Player {
        return players[nth]
    }

    fun size() = players.size

    fun statesOfCards(): String {
        return players.joinToString("\n") { "${it}카드: ${it.stateOfCards()}" }
    }

    fun compareWith(dealer: Dealer): Int {
        if (dealer.totalScore() > MAXIMUM_GAME_SCORE) {
            players.forEach { it.win() }
        }

        players.filter { player ->
            MAXIMUM_GAME_SCORE >= player.totalScore() &&
                player.totalScore() >= dealer.totalScore()
        }.forEach { it.win() }

        return players.count { it.isWin() }
    }

    override fun toString(): String {
        return players.joinToString()
    }
}
