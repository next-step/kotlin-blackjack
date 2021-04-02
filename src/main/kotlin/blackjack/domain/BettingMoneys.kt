package blackjack.domain

class BettingMoneys(private val moneys: List<BettingMoney>) {
    fun sum(): BettingMoney = BettingMoney(moneys.sumOf { it.money })
}
