package blackjack.domain

import blackjack.domain.card.Card

private const val NEED_FIRST_TURN_CARD_NUMBER = 2
private const val NEED_MIN_DRAW_CARD_NUMBER = 1

class Deck(
    cards: List<Card>,
) {
    private val _cards: MutableList<Card> = cards.toMutableList()

    fun pullOutFirstTurn(): List<Card> {
        require(_cards.size >= NEED_FIRST_TURN_CARD_NUMBER) { "나눠줄 덱의 카드가 부족합니다. 현재 카드 수 : ${_cards.size}장" }
        return listOf(
            _cards.removeFirst(),
            _cards.removeFirst(),
        )
    }

    fun pullOut(): Card {
        require(_cards.size >= NEED_MIN_DRAW_CARD_NUMBER) { "덱의 카드가 비었습니다." }
        return _cards.removeFirst()
    }

    companion object {
        fun createOf() = Deck(Card.ALL_CARDS.shuffled())
    }
}
