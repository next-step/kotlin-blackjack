package blackjack.domain.participantion

class Price(amount: Int) {
    var amount: Int = amount
        private set

    init {
        require(amount >= 0) { VALID_MESSAGE }
    }

    fun inc(price: Price) {
        require(price.amount >= 0) { VALID_MESSAGE }
        this.amount += price.amount
    }

    fun dec(price: Price) {
        require(price.amount >= 0) { VALID_MESSAGE }
        this.amount -= price.amount
    }

    operator fun plus(price: Price): Price = Price(this.amount + price.amount)

    companion object {
        private const val VALID_MESSAGE = "금액은 0 이상 입니다."
    }
}
