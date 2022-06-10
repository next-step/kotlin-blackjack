package balckjac.domain

import balckjac.domain.GameRule.TOTAL_CARD_COUNT

class Deck(
    cards: List<Card> = emptyList()
) {
    private val _cards = cards.toMutableList()
    val cards: List<Card> get() = _cards.toList()

    init {
        require(cards.size == TOTAL_CARD_COUNT) {
            "카드는 ${TOTAL_CARD_COUNT}으로 구성되어야 합니다."
        }
    }
}
