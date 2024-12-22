package blackjack.domain.player

import blackjack.domain.CardDeck

class Player(name: String, initBet: Float = 0f) : Participant(name, initBet) {
    override fun startTurn(
        onTurnStarted: ((Participant) -> String)?,
        onPrintResultCallback: (Participant) -> Unit,
    ) {
        while (!isBust() && onTurnStarted?.invoke(this) == YES) {
            val card = CardDeck.drawCard()
            drawCard(card)
            onPrintResultCallback(this)
        }
    }
}
