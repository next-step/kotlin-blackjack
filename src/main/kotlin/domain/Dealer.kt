package domain

class Dealer : AbstractCardHolder() {
    fun calculateProfit(players: List<Player>): Int {
        return players.sumOf { -it.calculateFinalProfit() }
    }
}
