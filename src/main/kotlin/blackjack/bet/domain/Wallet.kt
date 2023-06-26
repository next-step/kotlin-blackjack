package blackjack.bet.domain

class Wallet {

    private var balance = 0
    private var income = 0
    fun charge(money: Int) {
        this.balance = money
    }

    fun balance(): Int {
        return balance
    }

    fun income(): Int {
        return income
    }

    fun settle(result: Int) {
        balance += result
        income += result
    }
}
