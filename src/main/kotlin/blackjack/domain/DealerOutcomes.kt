package blackjack.domain

class DealerOutcomes(val results: List<Result>) {
    fun numberOfWins(): Int = results.count { it == Result.WIN }

    fun numberOfLose(): Int = results.count { it == Result.LOSE }
}
