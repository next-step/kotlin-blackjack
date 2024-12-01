package blackjack.domain

class Deck(
    private val deck: MutableList<BlackjackCard> = BlackjackCard.defaultSet.shuffled().toMutableList(),
) {
    val size
        get() = deck.size

    fun draw(): BlackjackCard = runCatching { deck.removeFirst() }
        .getOrElse { throw IllegalStateException("[Dealer] 더이상 남은 패가 존재하지 않습니다.") }
}
