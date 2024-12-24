package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Card.Companion.SpecialNumber
import blackjack.domain.CardDeck
import blackjack.domain.status.PlayerStatus
import java.math.BigDecimal

abstract class Participant(val name: String, val initBet: BigDecimal) {
    private var cards = mutableListOf<Card>()
    var playerStatus: PlayerStatus = PlayerStatus.HIT
    var balance: BigDecimal = BigDecimal.ZERO

    abstract fun startTurn(
        onTurnStarted: ((Participant) -> String)?,
        onPrintResultCallback: (Participant) -> Unit,
    )

    abstract fun isDealer(): Boolean

    fun isBust(): Boolean {
        return playerStatus == PlayerStatus.BUST
    }

    fun updateStatus(status: PlayerStatus){
        playerStatus = status
    }

    fun drawCard(newCard: Card) {
        cards.add(newCard)
    }

    fun initTurn(onPrintResultCallback: (Participant) -> Unit) {
        repeat(2) { drawCard(CardDeck.drawCard()) }
        onPrintResultCallback(this)
    }

    fun calculateCard(): Int {
        val aceCards = cards.filter { it.number == SpecialNumber.A.name }
        val currentSum = cards.sumOf { card -> card.getCardNumber(card.number) }

        val totalSum = aceCards.fold(currentSum) { sum, _ ->
            if (sum + 10 <= 21) sum + 10 else sum
        }

        return totalSum
    }

    fun getAllCards(): List<Card> {
        return cards.toList()
    }

    fun updateBalance(money: BigDecimal) {
        balance = money
    }

    companion object {
        const val YES = "y"
        const val NO = "n"
    }
}
