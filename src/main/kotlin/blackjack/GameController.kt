package blackjack

import blackjack.domain.game.Result
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Dealer.Companion.FIRST_DRAW_NUMBER
import blackjack.domain.participant.Player
import blackjack.domain.participant.ParticipantStatus
import blackjack.view.ViewResolver

class GameController(
    private val players: List<Player>,
    private val viewResolver: ViewResolver,
) {
    private val dealer: Dealer = Dealer()
    private val result: Result = Result(dealer, players)

    fun start() {
        initialHand()
        play()
        viewResolver.printResult(dealer, players, result)
    }

    private fun initialHand() {
        val dealerCards = dealer.drawCards(FIRST_DRAW_NUMBER)
        dealer.addCards(*dealerCards.toTypedArray())
        players.forEach { player ->
            val cards = dealer.drawCards(FIRST_DRAW_NUMBER)
            player.addCards(*cards.toTypedArray())
        }
        viewResolver.printInitialHand(dealer, players)
    }

    private fun play() {
        players.forEach { player -> player.turn() }
        dealer.turn()
        players.forEach { player -> result.decideWinner(dealer, player) }
    }

    private fun Player.turn() {
        do {
            val isHit = viewResolver.decidePlayerHitDecision(this)
            if (isHit) {
                val card = dealer.drawOneCard()
                addCards(card)
            } else {
                changeStatus(ParticipantStatus.STAND)
            }
            viewResolver.printParticipantInfo(this)
        } while (isDrawable())
    }

    private fun Dealer.turn() {
        while (isDrawable()) {
            val card = dealer.drawOneCard()
            dealer.addCards(card)
            viewResolver.printDealerDrawOneCard()
        }
    }
}
