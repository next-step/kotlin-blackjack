package blackjack.domain

object CardsFactory {

    fun makeCards(cards: Pair<Card, Card>): Cards {
        require(cards.first != cards.second) { "동일한 카드로는 생성할 수 없습니다. cards.first: ${cards.first}, cards.second: ${cards.second}" }
        return Cards(listOf(cards.first, cards.second))
    }
}
