package blackjack.domain

class PlayerCards(list: List<Card> = emptyList()) {
    private val _cards = list.toMutableList()
    val cards: List<Card> get() = _cards

    val score: Score get() = Score.sumBy(_cards.map { it.score }, hasSoftCard())
    val size: Int get() = _cards.size

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun isBust(): Boolean = score.isBust()
    fun isBlackJack(): Boolean = cards.size == 2 && score.isBlackJack()

    private fun hasSoftCard() = _cards.any { it.isAceCard() }
}
