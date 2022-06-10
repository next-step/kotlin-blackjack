package balckjac.domain

import balckjac.domain.GameRule.TOTAL_CARD_COUNT


class Deck(
    cards: List<Card> = CARD_LIST
) {
    private val _cards = cards.toMutableList()
    val cards: List<Card> get() = _cards.toList()

    init {
        require(cards.size == TOTAL_CARD_COUNT) {
            "카드는 ${TOTAL_CARD_COUNT}으로 구성되어야 합니다."
        }
    }


    fun draw(): Card {
        require(cards.isNotEmpty()) {
            "사용할 카드가 없습니다."
        }

        return cards.first().apply {
            _cards.remove(this)
        }
    }

    companion object {
        val CARD_LIST: List<Card> = Suit.values().map { character ->
            Denomination.values().map { number ->
                Card(suit = character, denomination = number)
            }
        }.flatten().shuffled()
    }
}
