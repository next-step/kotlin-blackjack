package blackjack.domain

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player

class BlackjackGame(
    private val deck: Deck,
    val participants: Participants,
) {
    fun start() {
        participants.drawInitCards(deck)
    }

    fun isPlayerTurn(): Boolean {
        return participants.isExistWaitingPlayer()
    }

    fun askDrawToCurrentTurnPlayer(isDrawCard: Boolean) {
        val player = findCurrentTurnPlayer()
        if (isDrawCard) {
            player.drawCard(deck.pullOut())
            return
        }
        player.endOwnTurn()
    }

    fun findCurrentTurnPlayer(): Player = participants.findCurrentTurnPlayer() as Player

    fun isSatisfiedDealerPullOutCondition(): Boolean {
        val dealer = findDealer()
        val done = dealer.isAbleToDraw()
        if (done) {
            dealer.drawCard(deck.pullOut())
        }

        return done
    }

    private fun findDealer(): Dealer = participants.findDealer() as Dealer
}
