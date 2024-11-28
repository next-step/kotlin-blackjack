package blackjack.domain

class Player(
    val name: String,
    val cards: Cards = Cards(),
) {

    fun receiveCard(card: Card) {
        checkCanReceiveCard()
        cards.add(card)
    }

    fun canDrawCard(): Boolean {
        return !cards.isOverMaxScore()
    }

    private fun checkCanReceiveCard() {
        if (!canDrawCard()) {
            throw IllegalStateException(
                "21점을 초과하여 카드를 받을 수 없습니다. 현재 점수: ${cards.calculateScore()}",
            )
        }
    }
}
