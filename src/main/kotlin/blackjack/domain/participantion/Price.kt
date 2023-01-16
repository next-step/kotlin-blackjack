package blackjack.domain.participantion

class Price(amount: Int) {
    var amount: Int = amount
        private set

    init {
        require(amount >= 0) { VALID_MESSAGE }
    }

    fun unaryMinus() {
        this.amount = amount.unaryMinus()
    }

    fun increase(price: Price) {
        require(price.amount >= 0) { VALID_MESSAGE }
        this.amount += price.amount
    }

    fun decrease(price: Price) {
        require(price.amount >= 0) { VALID_MESSAGE }
        this.amount -= price.amount
    }

    operator fun plus(price: Price): Price = Price(this.amount + price.amount)

    companion object {
        private const val VALID_MESSAGE = "금액은 0 이상 입니다."
        val ZERO = Price(0)
    }
}
