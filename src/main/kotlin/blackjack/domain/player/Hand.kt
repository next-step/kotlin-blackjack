package blackjack.domain.player

import blackjack.domain.deck.Card

class Hand {
    var status: HandStatus = HandStatus.GENERAL
        private set

    private val _cards: ArrayList<Card> = arrayListOf()

    val cards: List<Card>
        get() = _cards

    fun addNew(card: Card): HandStatus {
        if (hasFreeSpace()) {
            _cards.add(card)
            changeStatus()
        }
        return status
    }

    fun hasFreeSpace() = status == HandStatus.GENERAL

    fun calculate(): Int {
        val sortedCards = _cards.sortedBy { it.getScore() }
        return sortedCards.fold(0) { acc, card -> acc + card.getScore(acc) }
    }

    private fun changeStatus() {
        status = HandStatus.of(calculate())
    }
}
