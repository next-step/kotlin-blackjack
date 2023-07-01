package blackjack.domain.model

class Result(profit: Money = Money(0), val record: Record = Record()) {
    var profit: Money = profit
        private set

    fun addMoney(amount: Money) {
        this.profit += amount
    }

    fun minusMoney(amount: Money) {
        this.profit -= amount
    }

    override fun toString(): String {
        return profit.money.toString()
    }
}
