package blackjack.domain

open class Person(
    open val name: String = "",
    open val ownCards: OwnCards = OwnCards(Draw.FIRST_DRAW_COUNT),
    private var _money: Double = 0.0,
    open val isBlackJack: Boolean = (ownCards.sumCardNumber() == Draw.DRAW_LIMIT),
    private var _isDrawable: Boolean = true
) {
    val money: Double
        get() = _money

    val isDrawable: Boolean
        get() = _isDrawable

    fun addCard(drawYn: Boolean) {
        if (drawYn) {
            ownCards.addCard(Card())
            changeDrawable()
        }
    }

    fun changeDrawable(isDrawable: Boolean = (ownCards.sumCardNumber() < Draw.DRAW_LIMIT)) {
        _isDrawable = isDrawable
    }

    fun changeMoney(money: Double) {
        _money += money
    }
}
