package blackJack.domain

object TotalScore {
    fun get(hands: List<Card>): Int = hands.map { it.getNumber() }.sum()
}
