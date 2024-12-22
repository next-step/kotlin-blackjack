package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Card.Companion.SpecialNumber
import blackjack.domain.CardDeck
import blackjack.domain.status.GameResult
import blackjack.domain.status.PlayerStatus
import blackjack.domain.status.ResultRecord
import java.math.BigDecimal

abstract class Participant(val name: String, val initBet: BigDecimal) {
    private var cards = mutableListOf<Card>()
    val gameResult = ResultRecord(0, 0)
    var playerStatus: PlayerStatus = PlayerStatus.HIT
    var balance: BigDecimal = BigDecimal.ZERO

    abstract fun startTurn(
        onTurnStarted: ((Participant) -> String)?,
        onPrintResultCallback: (Participant) -> Unit,
    )

    abstract fun isDealer(): Boolean

    fun isBust(): Boolean {
        return calculateCard() > 21
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

    fun updateWinningStatus(
        result: GameResult,
        count: Int = 1,
    ) {
        gameResult.updateResult(result, count)
    }

    fun updateBalance(money: BigDecimal) {
        balance = money
    }

    companion object {
        const val YES = "y"
    }
}
