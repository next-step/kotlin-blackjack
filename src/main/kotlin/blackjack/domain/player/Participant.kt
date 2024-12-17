package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Card.Companion.SpecialNumber
import blackjack.domain.CardDeck

abstract class Participant(val name: String) {
    protected var cards = mutableListOf<Card>()
    var winCount = 0
    var loseCount = 0

    abstract fun startTurn(
        onTurnStarted: ((Participant) -> String)?,
        onPrintResultCallback: (Participant) -> Unit,
    )

    abstract fun showCards()

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
        var currentSum = cards.sumOf { card -> card.getCardNumber(card.number) }

        aceCards.forEach { _ ->
            if (currentSum + 10 <= 21) currentSum += 10
        }

        return currentSum
    }

    fun getAllCards(): List<Card> {
        return cards.toList()
    }

    fun updateWinningStatus(winCount: Int, loseCount: Int) {
        this.winCount = winCount
        this.loseCount = loseCount
    }

    companion object {
        const val YES = "y"
    }
}
