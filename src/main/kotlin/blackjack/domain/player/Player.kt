package blackjack.domain.player

import blackjack.domain.CardDeck

class Player(name: String) : Participant(name) {
    override fun startTurn(
        onTurnStarted: ((Participant) -> String)?,
        onPrintResultCallback: (List<Participant>) -> Unit,
    ) {
        while (!isBust() && onTurnStarted?.invoke(this) == YES) {
            val card = CardDeck.drawCard()
            drawCard(card)
            onPrintResultCallback(listOf(this))
        }
    }
}
