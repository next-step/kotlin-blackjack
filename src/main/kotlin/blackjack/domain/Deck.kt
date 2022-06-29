package blackjack.domain

import blackjack.domain.card.Card

private const val NEED_FIRST_TURN_CARD_NUMBER = 2
private const val NEED_MIN_DRAW_CARD_NUMBER = 1

class Deck(
    private val cards: MutableList<Card>,
) {
    fun pullOutFirstTurn(): List<Card> {
        require(cards.size >= NEED_FIRST_TURN_CARD_NUMBER) { "나눠줄 덱의 카드가 부족합니다. 현재 카드 수 : ${cards.size}장" }
        return listOf(
            cards.removeFirst(),
            cards.removeFirst(),
        )
    }

    fun pullOut(): Card {
        require(cards.size >= NEED_MIN_DRAW_CARD_NUMBER) { "덱의 카드가 비었습니다." }
        return cards.removeFirst()
    }

    companion object {
        fun createOf() = Deck(Card.ALL_CARDS.shuffled().toMutableList())
    }
}
