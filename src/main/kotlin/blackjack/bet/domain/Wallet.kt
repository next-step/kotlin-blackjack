package blackjack.bet.domain

class Wallet {

    private var bettingAmount = 0
    private var income = 0
    fun charge(money: Int) {
        this.bettingAmount = money
    }

    fun balance(): Int {
        return bettingAmount
    }

    fun income(): Int {
        return income
    }

    fun settle(result: Int) {
        bettingAmount += result
        income += result
    }
}
