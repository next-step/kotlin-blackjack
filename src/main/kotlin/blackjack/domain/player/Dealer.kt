package blackjack.domain.player

import blackjack.domain.CardDeck

class Dealer(name: String = DEALER_NAME) : Participant(name) {
    private fun shouldDrawCard(): Boolean {
        return calculateCard() <= DRAW_THREAD
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

    override fun showCards() {
        println("$name: ${getAllCards()[0].printCard()}")
    }

    companion object {
        const val DEALER_NAME = "딜러"
        const val DRAW_THREAD = 16
    }
}
