package blackjack.domain

import blackjack.domain.Game.Companion.DEFAULT_CARD_AMOUNT

data class Players(private val players: List<Player>) {

    fun setUpWithCards() {
        players.forEach { player -> (1..DEFAULT_CARD_AMOUNT).map { player.draw(Dealer.giveCard()) } }
    }

    fun findPlayer(nth: Int): Player {
        return players[nth]
    }

    fun size() = players.size

    fun statesOfCards(): String {
        return players.joinToString("\n") { "${it}카드: ${it.stateOfCards()}" }
    }

    override fun toString(): String {
        return players.joinToString()
    }
}
