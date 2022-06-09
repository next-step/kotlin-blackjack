package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.card.CardSymbol
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.ParticipantStatus
import blackjack.view.ViewResolver

class Game(
    private val players: List<Player>,
    private val viewResolver: ViewResolver
) {
    private val dealer: Dealer = Dealer()
    private val result: Result = Result()

    fun start() {
        initialHand()
        play()
        viewResolver.resultView.printResult(dealer, players, result)
    }

    private fun initialHand() {
        val dealerCards = dealer.drawCards(FIRST_DRAW_NUMBER)
        dealer.addCards(*dealerCards.toTypedArray())
        players.forEach { player ->
            val cards = dealer.drawCards(FIRST_DRAW_NUMBER)
            player.addCards(*cards.toTypedArray())
        }
        viewResolver.participantView.printInitialHand(dealer, players)
    }

    private fun play() {
        players.forEach { player -> player.turn() }
        dealer.turn()

        players.forEach { player -> result.checkWinner(dealer, player) }
    }

    private fun Player.turn() {
        do {
            val isHit = viewResolver.inputView.decidePlayerHitDecision(this)
            if (isHit) {
                val card = dealer.drawOneCard()
                addCards(card)
            } else {
                changeStatus(ParticipantStatus.STAND)
            }
            viewResolver.participantView.printParticipantInfo(this)
        } while (isDrawable())
    }

    private fun Dealer.turn() {
        while (isDrawable()) {
            val card = dealer.drawOneCard()
            dealer.addCards(card)
            viewResolver.participantView.printDealerDrawOneCard()
        }
    }

    companion object {
        const val ACE_MIN_NUMBER: Int = 1
        const val ACE_MAX_NUMBER: Int = 11
        const val FIRST_DRAW_NUMBER = 2
        val ALL_CARDS = CardSuit.values().flatMap { suit ->
            CardSymbol.values().map { symbol ->
                Card(suit, symbol)
            }
        }
    }
}
