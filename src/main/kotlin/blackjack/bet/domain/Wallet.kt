package blackjack.bet.domain

class Wallet {

    private var money = 0
    private var income = 0
    fun charge(money: Int) {
        this.money = money
    }

    fun bet(): Int {
        return money
    }

    fun income(): Int {
        return income
    }

    fun settle(result: Int) {
        money += result
        income += result
    }
}
