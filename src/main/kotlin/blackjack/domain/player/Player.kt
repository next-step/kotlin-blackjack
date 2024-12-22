package blackjack.domain.player

import blackjack.domain.CardDeck
import java.math.BigDecimal

class Player(name: String, initBet: BigDecimal = BigDecimal.ZERO) : Participant(name, initBet) {
    override fun startTurn(
        onTurnStarted: ((Participant) -> String)?,
        onPrintResultCallback: (Participant) -> Unit,
    ) {
        while (!isBust() && onTurnStarted?.invoke(this) == YES) {
            val card = CardDeck.drawCard()
            drawCard(card)
            updateStatus(playerStatus.calculateStatus(this))
            onPrintResultCallback(this)
        }
    }

    override fun isDealer(): Boolean {
        return false
    }
}
