package blackjack.domain.player

import blackjack.domain.CardDeck

class Player(name: String) : AbstractPlayer(name) {
    override fun startTurn(
        onTurnStarted: ((AbstractPlayer) -> String)?,
        onPrintResultCallback: (List<AbstractPlayer>) -> Unit,
    ) {
        while (!isBust() && onTurnStarted?.invoke(this) == YES) {
            val card = CardDeck.drawCard()
            drawCard(card)
            onPrintResultCallback(listOf(this))
        }
    }
}
