package blackjack.domain.member

import blackjack.domain.GameState

class Players(
    val items: List<Player>
) {
    init {
        require(items.size >= MIN_SIZE) { "참가자는 최소 두명이상 이어야 해요." }
    }

    val size: Int
        get() = items.size

    fun toResultPlayers(dealer: Dealer): ResultPlayers {
        val blackjackPlayers = blackjackPlayers()

        if (dealer.isOverBlackjackNumber()) {
            return blackjackPlayers + nonBlackjackPlayers()
        }

        if (dealer.blackjack()) {
            return blackjackPlayers.toWinnerPlayers() + losePlayers(dealer)
        }

        return blackjackPlayers + winnerPlayers(dealer) + losePlayers(dealer)
    }

    private fun winnerPlayers(dealer: Dealer): ResultPlayers {
        return ResultPlayers(
            items.filter { it.isWin(dealer) }.map { ResultPlayer(it, GameState.WIN) }
        )
    }

    private fun losePlayers(dealer: Dealer): ResultPlayers {
        return ResultPlayers(
            items.filter { it.isLose(dealer) }.map { ResultPlayer(it, GameState.LOSE) }
        )
    }

    private fun blackjackPlayers(): ResultPlayers {
        return ResultPlayers(
            items.filter { it.blackjack() }.map { ResultPlayer(it, GameState.WIN_BLACKJACK) }
        )
    }

    private fun nonBlackjackPlayers(): ResultPlayers {
        return ResultPlayers(
            items.filter { !it.blackjack() }.map { ResultPlayer(it, GameState.WIN) }
        )
    }

    companion object {
        private const val MIN_SIZE = 2
    }
}
