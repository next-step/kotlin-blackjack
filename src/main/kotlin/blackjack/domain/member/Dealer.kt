package blackjack.domain.member

import blackjack.domain.Cards
import blackjack.domain.Deck
import blackjack.domain.GameResult

class Dealer(
    override val cards: Cards
) : Member {
    override val name: String
        get() = DEFAULT_NAME

    override fun ableMoreDrawCard() = resultScore() < DRAW_LIMIT_SCORE
    override fun conditionOfWin(otherMember: Member): Boolean {
        if (this.isEqualNumberThan(otherMember)) {
            return true
        }

        return this.isNearBlackJackThan(otherMember)
    }

    fun gameResult(players: Players): GameResult {
        if (isOverBlackjackNumber()) {
            return GameResult.winAllPlayers(players)
        }

        return GameResult(
            winnerPlayers(players),
            losePlayers(players)
        )
    }

    private fun winnerPlayers(players: Players): WinnerPlayers {
        return WinnerPlayers(players.items.filter { player -> player.isWin(this) })
    }

    private fun losePlayers(players: Players): LosePlayers {
        return LosePlayers(players.items.filter { player -> player.isLose(this) })
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
