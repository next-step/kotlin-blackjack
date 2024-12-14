package blackjack.card

import blackjack.card.Cards.Companion.createShuffledCardPack

data class Deck(
    private val _cards: MutableList<Card> = createShuffledCardPack().toMutableList()
) {
    val cards: List<Card>
        get() = _cards.toList()

    init {
        require(_cards.size == NUMBER_OF_CARDS) { "카드의 수가 52장이 아닙니다." }
    }

    override fun toString(): String {
        return _cards.joinToString("\n")
    }

    fun pick(): Card {
        require(_cards.size >= DEFAULT_PICK_COUNT) { "더 이상 뽑을 카드가 없습니다." }

        val card = _cards.take(DEFAULT_PICK_COUNT).first()
        _cards.remove(card)
        return card
    }

    companion object {
        private const val NUMBER_OF_CARDS = 52
        private const val DEFAULT_PICK_COUNT = 1
    }
}
