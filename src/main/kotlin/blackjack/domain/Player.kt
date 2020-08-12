package blackjack.domain

const val BLACKJACK_POINT = 21

open class Player(val name: String) {
    var cards: List<Card> = emptyList()
        private set
    var isHit: Boolean = true
    var isBusted: Boolean = false
        private set
    var point: Int = 0
        private set
    var playResult: PlayResultType = PlayResultType.DRAW

    open fun calculatePoint(aceToBig: Boolean = false): Int {
        return cards.sumBy { it.getPoint(aceToBig) }
    }

    open fun addCard(card: Card) {
        val currentCards = cards.toMutableList()
        currentCards.add(card)
        cards = currentCards.toList()
        point = calculatePoint()
        isBusted = point > BLACKJACK_POINT
        if (isBusted) playResult = PlayResultType.LOSE
    }

    fun checkResult(dealerPoint: Int) {
        if (!isBusted && point > dealerPoint) playResult = PlayResultType.WIN
    }
}
