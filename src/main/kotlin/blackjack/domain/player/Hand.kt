package blackjack.domain.player

import blackjack.domain.deck.Card
import blackjack.domain.game.ScoreCalculator

class Hand {
    var status: HandStatus = HandStatus.GENERAL
        private set

    private val cards = arrayListOf<Card>()

    fun getCards(): List<Card> = cards.toList()

    fun addNew(card: Card): HandStatus {
        if (hasFreeSpace()) {
            cards.add(card)
            changeStatus()
        }
        return status
    }

    fun hasFreeSpace() = status == HandStatus.GENERAL

    private fun changeStatus() {
        status = HandStatus.of(ScoreCalculator.calculate(this))
    }
}
