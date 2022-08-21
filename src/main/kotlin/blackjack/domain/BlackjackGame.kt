package blackjack.domain

import blackjack.domain.gameresult.GameResults
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.Players

class BlackjackGame(
    private val deck: Deck,
    val players: Players,
    val dealer: Dealer,
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
        val drawable = dealer.isAbleToDraw()
        if (drawable) {
            dealer.drawCard(deck.pullOut())
        }

        return drawable
    }

    fun getGameResults(): List<GameResults> {
        return (dealer.decideWinOrLoseResults(players.players) + dealer.getDealerResult(players.players))
    }
}
