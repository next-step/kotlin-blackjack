package blackjack.domain

fun defaultDeckGenerator(): MutableList<Card> {
    return createAllCards().toMutableList()
}

fun shuffledDeckGenerator(): MutableList<Card> {
    return createAllCards().shuffled().toMutableList()
}

private fun createAllCards(): List<Card> {
    return Suit.entries.flatMap { createAllCardsBySuit(it) }
}

private fun createAllCardsBySuit(suit: Suit): List<Card> {
    return Rank.entries.map { Card(suit, it) }
}
