package blackjack

class DealerAdjustment(private val playerResult: List<PlayerResult>, private val bet: List<Bet>) {
    fun income(): Int {
        return playerResult.sortedBy { it.name }.zip(bet.sortedBy { it.name })
            .onEach { require(it.first.name == it.second.name) }
            .map { (pr, bet) -> pr.income(bet) }
            .sumBy { -it }
    }
}
