package blackjack.domain

const val BLACKJACK_POINT = 21

open class Player(val name: String) {
    private val _card = mutableListOf<Card>()
    val cards: List<Card>
        get() = _card.toList()
    var isHit: Boolean = true
    var point: Int = 0
        private set
    var playResult: PlayResultType = PlayResultType.DRAW

    open fun calculatePoint(aceToBig: Boolean = false): Int {
        return cards.sumBy { it.getPoint(aceToBig) }
    }

    open fun addCard(card: Card) {
        _card.add(card)
        point = calculatePoint()
    }

    fun checkResult(dealerPoint: Int) {
        if (isBusted()) playResult = PlayResultType.LOSE
        if (point > dealerPoint) playResult = PlayResultType.WIN
        else if (point < dealerPoint) playResult = PlayResultType.LOSE
    }

    fun isBusted(): Boolean = point > BLACKJACK_POINT
}
