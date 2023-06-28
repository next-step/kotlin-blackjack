package blackjack.domain.gamer

import blackjack.domain.card.InitCard
import blackjack.domain.game.DealerMatchResult
import blackjack.domain.game.MatchResult

@JvmInline
value class Players(val value: List<Player>) {

    fun init(cardPick: () -> InitCard) {
        value.forEach { player ->
            player.init(cardPick())
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
        val dealerMatchResult = DealerMatchResult(
            winCount = playerMatchResults.count { it.matchResultType.isLose() },
            tieCount = playerMatchResults.count { it.matchResultType.isTie() },
            loseCount = playerMatchResults.count { it.matchResultType.isWin() }
        )
        return MatchResult(
            dealerCards = dealer.state.cards,
            allPlayerCards = captureAllPlayerCards(),
            dealerMatchResult = dealerMatchResult,
            playerMatchResults = playerMatchResults,
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
