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
        var point = cards.sumBy { it.getPoint(true) }
        if (point == BLACKJACK_POINT) return point
        point = cards.sumBy { it.getPoint(point < BLACKJACK_POINT) }
        return point
    }

    open fun addCard(card: Card) {
        _card.add(card)
        point = calculatePoint()
    }

    fun calculateResult(dealerPoint: Int) {
        when {
            isBusted() -> playResult = PlayResultType.LOSE
            dealerPoint > BLACKJACK_POINT -> playResult = PlayResultType.WIN
            point > dealerPoint -> playResult = PlayResultType.WIN
            point < dealerPoint -> playResult = PlayResultType.LOSE
            point == dealerPoint -> playResult = PlayResultType.DRAW
        }
        if (isBlackJack()) {
            playResult = PlayResultType.BLACKJACK
        }
    }

    private fun isBlackJack(): Boolean =
        playResult != PlayResultType.DRAW && point == BLACKJACK_POINT && cards.size == 2

    fun isBusted(): Boolean = point > BLACKJACK_POINT

    fun isFinished(): Boolean = isBusted() || !isHit
}
