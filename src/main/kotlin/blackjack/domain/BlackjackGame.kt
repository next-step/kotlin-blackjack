package blackjack.domain

import blackjack.domain.gameresult.GameResult
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.Players

class BlackjackGame(
    private val deck: Deck,
    val players: Players,
    val dealer: Dealer
) {
    fun start() {
        players.drawInitCards(deck)
        dealer.drawCards(deck.pullOutFirstTurn())
    }

    fun isPlayerTurn(): Boolean {
        return players.isExistWaitingPlayer()
    }

    fun askDrawToCurrentTurnPlayer(isDrawCard: Boolean) {
        val player = findCurrentTurnPlayer()
        if (isDrawCard) {
            player.drawCard(deck.pullOut())
            return
        }
        player.endOwnTurn()
    }

    fun findCurrentTurnPlayer(): Player = players.findCurrentTurnPlayer()

    fun isSatisfiedDealerPullOutCondition(): Boolean {
        if (dealer.isAbleToDraw()) {
            dealer.drawCard(deck.pullOut())
            return true
        }

        return false
    }

    fun getGameResults(): List<GameResult> {
        return (dealer.decideWinOrLoseResults(players.players) + dealer.getDealerResult(players.players))
    }
}
