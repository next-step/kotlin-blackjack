package blackjack.domain.gamer

import blackjack.domain.card.Cards
import blackjack.domain.game.DealerMatchResult
import blackjack.domain.game.GamerCards
import blackjack.domain.game.GamerMatchResult
import blackjack.domain.game.MatchResult

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

    fun match(dealer: Dealer): MatchResult {
        val playerMatchResults = value.map { player -> player.match(dealer) }
        val dealerMatchResult = DealerMatchResult.create(playerMatchResults)
        return MatchResult(
            gamerCards = GamerCards(
                dealerCards = dealer.state.cards,
                allPlayerCards = captureAllPlayerCards(),
            ),
            gamerMatchResult = GamerMatchResult(
                dealerMatchResult = dealerMatchResult,
                playerMatchResults = playerMatchResults,
            ),
        )
    }

    private fun findWaitPlayerOrNull(): Player? {
        return value.firstOrNull { it.state.isHit() }
    }

    companion object {

        fun create(playerNames: PlayerNames): Players {
            return Players(playerNames.map { Player(it) })
        }
    }
}
