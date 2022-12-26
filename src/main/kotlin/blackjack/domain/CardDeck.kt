package blackjack.domain

class CardDeck(
    private val deck: MutableList<Card> = Card.values().toMutableList()
        .apply { shuffle() },
) {

    fun deal(): Card = deck.removeFirstOrNull() ?: throw IllegalStateException("더 이상 카드가 없습니다.")

    fun deal(count: Int) = (1..count).map { deal() }
        .toSet()
}
