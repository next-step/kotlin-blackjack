package blackjack.domain.gamer

import blackjack.domain.card.Cards

@JvmInline
value class Players(val value: List<Player>) {

    fun init(pickCards: () -> Cards) {
        value.forEach { player ->
            player.init(pickCards())
        }
    }

    fun notHasCards(): Boolean {
        return value.all { it.notHasCards() }
    }

    fun hasWaitPlayer(): Boolean {
        return findWaitPlayerOrNull() != null
    }

    fun requireWaitPlayer(): Player {
        return findWaitPlayerOrNull() ?: throw IllegalStateException("wait player not existed")
    }

    fun captureAllPlayerCards(): List<PlayerCards> {
        return value.map { it.captureCards() }
    }

    private fun findWaitPlayerOrNull(): Player? {
        return value.firstOrNull { it.state.isHit() }
    }

    companion object {

        fun create(playerInitProperties: List<PlayerInitProperty>): Players {
            val players = playerInitProperties.map { Player(it.playerName, it.betAmount) }
            return Players(players)
        }
    }
}
