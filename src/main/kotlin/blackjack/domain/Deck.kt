package blackjack.domain

class Deck(
    private val _deck: List<BlackjackCard> = BlackjackCard.defaultSet.shuffled(),
) {
    private val deck
        get() = _deck.filterNot { it.isUsed }

    val size
        get() = deck.size

    fun draw(): BlackjackCard {
        val topOfCards = deck.firstOrNull()

        return topOfCards?.draw()
            ?: throw IllegalStateException("[Dealer] 더이상 남은 패가 존재하지 않습니다.")
    }
}
