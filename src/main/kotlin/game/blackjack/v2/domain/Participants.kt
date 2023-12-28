package game.blackjack.v2.domain

import game.blackjack.v2.domain.participant.Dealer
import game.blackjack.v2.domain.participant.GameResult.DRAW
import game.blackjack.v2.domain.participant.GameResult.LOSE
import game.blackjack.v2.domain.participant.GameResult.WIN
import game.blackjack.v2.domain.participant.Player

class Participants(val dealer: Dealer, val players: List<Player>) {

    fun distributeInitialCards() {
        dealer.drawCards(Deck.initialDraw())
        players.forEach { it.drawCards(Deck.initialDraw()) }
    }

    fun drawPlayerAdditionalCards(
        drawDecision: (Player) -> Boolean,
        result: (Player) -> Unit
    ) = players.forEach {
        while (it.isNotBust() && drawDecision(it)) {
            it.drawCard(Deck.drawOnce())
            result(it)
        }
    }

    fun drawDealerAdditionalCardsIfRequired(
        result: () -> Unit = {}
    ) {
        dealer.drawCardIfRequired(result)
    }

    fun finishGame() {
        players.forEach { player ->
            recordGameResult(player)
        }
    }

    private fun recordGameResult(player: Player) {
        when {
            dealer.isBust() || player.hasHigherScore(dealer) -> playerWins(player, dealer)
            player.isBust() || dealer.hasHigherScore(player) -> dealerWins(player, dealer)
            else -> draw(player, dealer)
        }
    }

    private fun playerWins(player: Player, dealer: Dealer) {
        player.recordResult(WIN)
        dealer.recordResult(LOSE)
    }

    private fun dealerWins(player: Player, dealer: Dealer) {
        player.recordResult(LOSE)
        dealer.recordResult(WIN)
    }

    private fun draw(player: Player, dealer: Dealer) {
        player.recordResult(DRAW)
        dealer.recordResult(DRAW)
    }
}
 
