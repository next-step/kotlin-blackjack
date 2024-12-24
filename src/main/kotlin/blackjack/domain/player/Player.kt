package blackjack.domain.player

import blackjack.domain.CardDeck
import blackjack.domain.status.PlayerStatus
import java.math.BigDecimal

class Player(name: String, initBet: BigDecimal = BigDecimal.ZERO) : Participant(name, initBet) {
    override fun startTurn(
        onTurnStarted: ((Participant) -> String)?,
        onPrintResultCallback: (Participant) -> Unit,
    ) {
        if (onTurnStarted?.invoke(this) == NO) {
            playerStatus = PlayerStatus.STAY
            return
        }

        while (!isBust() && onTurnStarted?.invoke(this) == YES) {
            val card = CardDeck.drawCard()
            drawCard(card)
            updateStatus(playerStatus.checkStatus(this))
            onPrintResultCallback(this)
        }
    }

    override fun isDealer(): Boolean {
        return false
    }
}
