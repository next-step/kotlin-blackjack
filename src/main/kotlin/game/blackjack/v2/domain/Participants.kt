package game.blackjack.v2.domain

import game.blackjack.v2.domain.participant.Dealer
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
}
