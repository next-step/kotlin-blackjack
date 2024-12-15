package blackjack.domain.player

import blackjack.domain.CardDeck

class Dealer(name: String = DEALER_NAME) : Participant(name) {
    private fun shouldDrawCard(): Boolean {
        return calculateCard() <= 16
    }

    override fun startTurn(
        onTurnStarted: ((Participant) -> String)?,
        onPrintResultCallback: (List<Participant>) -> Unit,
    ) {
        while (!isBust() && shouldDrawCard()) {
            val card = CardDeck.drawCard()
            drawCard(card)
            onPrintResultCallback(listOf(this))
            onTurnStarted?.invoke(this)
        }
    }

    companion object {
        const val DEALER_NAME = "딜러"
    }
}
