package card

class Hand(
    val cards: Cards,
) {
    fun addCard(card: Card): Hand {
        require(cards.values.contains(card).not()) { "중복되는 카드는 추가할 수 없습니다." }
        check(cards.values.size >= MIN_NUMBER_OF_CARD_FOR_ONGOING_HAND) { "진행 중인 Hand는 두장 이상의 카드를 들고 있어야합니다." }
        return Hand(Cards(cards.values + card))
    }

    fun addTwoCards(twoCards: TwoCards): Hand {
        check(cards.values.isEmpty()) { "두장을 드로우 하려면 첫시작이어야 합니다." }
        return Hand(Cards(cards.values + twoCards.firstCard + twoCards.secondCard))
    }

    fun isBust(): Boolean {
        return cards.isBust()
    }

    companion object {
        private const val MIN_NUMBER_OF_CARD_FOR_ONGOING_HAND = 2
    }
}
