package blackjack.domain

sealed interface Card : Comparable<Card> {
    val symbol: SymbolType
    val priority: Int

    fun name(): String
    fun score(acc: Int = 0): Int

    override fun compareTo(other: Card): Int = priority compareTo other.priority
}
