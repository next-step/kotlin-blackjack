package blackjack.domain

class DealerOutcomes(val results: List<Result>) {
    fun numberOfWinds(): Int = results.count { it == Result.WIN }

    fun numberOfLose(): Int = results.count { it == Result.LOSE }
}
