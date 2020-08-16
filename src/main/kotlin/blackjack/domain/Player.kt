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
    var betMoney: BetMoney? = null

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

    fun getResult(dealerPoint: Int) {
        when {
            isBusted() -> playResult = PlayResultType.LOSE
            point > dealerPoint -> playResult = PlayResultType.WIN
            point < dealerPoint -> playResult = PlayResultType.LOSE
        }
    }

    fun isBusted(): Boolean = point > BLACKJACK_POINT

    private fun getRateOfReturn(): Double {
        if (playResult != PlayResultType.DRAW && point == BLACKJACK_POINT) return 1.5
        return when (playResult) {
            PlayResultType.LOSE -> -1.0
            PlayResultType.WIN -> 1.0
            PlayResultType.DRAW -> 0.0
        }
    }

    open fun getProfitMoney(): Int {
        if (betMoney == null) return 0
        return betMoney!!.money.times(getRateOfReturn()).toInt()
    }
}
