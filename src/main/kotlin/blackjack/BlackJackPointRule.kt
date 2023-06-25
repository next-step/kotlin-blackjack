package blackjack

object BlackJackPointRule {

    fun check(cards: PlayerCards): BlackJackStatus {
        val availablePoints = cards.map { it.value.numbers }.availableSum()
        return if (availablePoints.contains(21)) BlackJackStatus.BLACK_JACK
        else if (availablePoints.any { it < 21 }) BlackJackStatus.STAY_OR_HIT
        else BlackJackStatus.BUST
    }

    private fun List<List<Int>>.availableSum(): List<Int> {
        return this.fold(listOf(0)) { acc, ints -> acc.flatMap { a -> ints.map { a + it } } }
    }
}
