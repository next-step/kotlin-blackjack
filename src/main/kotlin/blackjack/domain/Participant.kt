package blackjack.domain

abstract class Participant(
    open val name: String,
    val cards: Cards = Cards()
) {
    abstract fun receiveCard(card: Card)
    abstract fun canReceiveOneMoreCard(): Boolean

    fun receiveInitialCards(cards: List<Card>) {
        require(cards.size == Deck.INITIAL_DEAL_SIZE) { "처음 받아야 할 카드 수는 ${Deck.INITIAL_DEAL_SIZE}장 입니다." }
        cards.forEach(::receiveCard)
    }
}
