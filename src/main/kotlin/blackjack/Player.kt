package blackjack

class Player(
    val name: String,
    val cards: Cards = Cards()
) {

    fun receiveCard(card: Card) {
        checkCanReceiveCard()
        cards.add(card)
    }

    private fun checkCanReceiveCard() {
        if (cards.isMaxScore()) {
            throw IllegalStateException(
                "21점을 초과하여 카드를 받을 수 없습니다. 현재 점수: ${cards.calculateScore()}"
            )
        }
    }
}
