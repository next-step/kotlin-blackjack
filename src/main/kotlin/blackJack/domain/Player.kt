package blackJack.domain

class Player(name: String) : Person(name) {
    var betMoney = DEFAULT_MONEY
        private set

    fun bettingMoney(money: Int) {
        betMoney = money
    }

    companion object {
        const val DEFAULT_MONEY = 0
    }
}
