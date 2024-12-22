package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Card.Companion.SpecialNumber
import blackjack.domain.CardDeck
import blackjack.domain.status.GameResult
import blackjack.domain.status.ResultRecord

abstract class Participant(val name: String, val initBet: Float) {
    protected var cards = mutableListOf<Card>()
    val gameResult = ResultRecord(0, 0)
    var betMoney: Float = 0f

    abstract fun startTurn(
        onTurnStarted: ((Participant) -> String)?,
        onPrintResultCallback: (Participant) -> Unit,
    )

    fun isBust(): Boolean {
        return calculateCard() > 21
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

    fun updateBetMoney(money: Float) {
        betMoney = money
    }

    companion object {
        const val YES = "y"
    }
}
