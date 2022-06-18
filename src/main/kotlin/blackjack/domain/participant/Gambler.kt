package blackjack.domain.participant

abstract class Gambler(
    profit: Money = Money.ZERO
) {
    var profit: Money = profit
        protected set

    fun earn(money: Money) {
        profit += money
    }

    fun lost(money: Money) {
        profit -= money
    }
}
