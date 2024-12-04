package blackjack.deck

import blackjack.card.Card

data class Cards(
    var cards: List<Card>,
) {
    fun draw(): Pair<Card, List<Card>> {
        require(cards.isNotEmpty()) { "카드 목록이 비어있습니다." }
        return (cards.first() to cards.drop(1))
    }

    fun isNotEmpty() = cards.isNotEmpty()
}
