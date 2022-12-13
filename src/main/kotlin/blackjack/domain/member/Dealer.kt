package blackjack.domain.member

import blackjack.domain.Cards
import blackjack.domain.Deck
import blackjack.domain.GameState

class Dealer(
    override val cards: Cards
) : Member {
    override val name: String
        get() = DEFAULT_NAME

    override fun ableMoreDrawCard() = resultScore() < DRAW_LIMIT_SCORE
    override fun conditionOfWin(otherMember: Member): Boolean {
        if (isEqualNumberThan(otherMember)) {
            return true
        }

        return isNearBlackJackThan(otherMember)
    }

    fun gameResultPlayers(players: Players): ResultPlayers {
        val blackjackPlayers = blackjackPlayers(players)

        if (isOverBlackjackNumber()) {
            return blackjackPlayers + nonBlackjackPlayers(players)
        }

        if (blackjack()) {
            return blackjackPlayers.toWinnerPlayers() + losePlayers(players)
        }

        return blackjackPlayers + winnerPlayers(players) + losePlayers(players)
    }

    private fun winnerPlayers(players: Players): ResultPlayers {
        return ResultPlayers(
            players.items.filter { it.isWin(this) }.map { ResultPlayer(it, GameState.WIN) }
        )
    }

    private fun losePlayers(players: Players): ResultPlayers {
        return ResultPlayers(
            players.items.filter { it.isLose(this) }.map { ResultPlayer(it, GameState.LOSE) }
        )
    }

    private fun blackjackPlayers(players: Players): ResultPlayers {
        return ResultPlayers(
            players.items.filter { it.blackjack() }.map { ResultPlayer(it, GameState.WIN_BLACKJACK) }
        )
    }

    private fun nonBlackjackPlayers(players: Players): ResultPlayers {
        return ResultPlayers(
            players.items.filter { !it.blackjack() }.map { ResultPlayer(it, GameState.WIN) }
        )
    }

    fun benefit(gameResultPlayers: ResultPlayers): Double {
        return gameResultPlayers.items.fold(0.0) { sum, resultPlayer -> sum + resultPlayer.benefit() }.unaryMinus()
    }

    companion object {
        fun init(deck: Deck): Dealer {
            val cards = deck.drawInitAssignCards()
            return Dealer(cards)
        }

        private const val DRAW_LIMIT_SCORE = 17
        private const val DEFAULT_NAME = "딜러"
    }
}
