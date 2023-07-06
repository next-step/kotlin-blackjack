package blackjack

/**
 * ### 플레이어가 소지한 카드를 표현하는 객체 입니다.
 *
 * 카드를 추가할 수 있고, 소지한 카드들에 대한 점수를 확인할 수 있습니다.
 */
class Deck(cards: List<Card> = listOf()) {

    private val _cards: MutableList<Card> = cards.toMutableList()

    val size: Int
        get() = _cards.size

    val point: Int
        get() = BlackjackCardPointCalculator.calculate(_cards)

    val cards: List<Card>
        get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }
}
