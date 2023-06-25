package blackjack.domain

object BlackJackPointRule {

    fun point(cards: PlayerCards): Int {
        val availablePoints = cards.map { it.value.numbers }.availableSum()
        return if (availablePoints.contains(21)) 21
        else if (availablePoints.any { it < 21 }) availablePoints.filter { it <= 21 }.max()
        else availablePoints.min()
    }

    fun check(cards: PlayerCards): BlackJackStatus {
        val point = point(cards)
        return if (point == 21) BlackJackStatus.BLACK_JACK
        else if (point < 21) BlackJackStatus.STAY_OR_HIT
        else BlackJackStatus.BUST
    }

    private fun List<List<Int>>.availableSum(): List<Int> {
        return this.fold(listOf(0)) { acc, ints -> acc.flatMap { a -> ints.map { a + it } } }
    }
}
