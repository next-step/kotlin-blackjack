package blackjack.domain

abstract class Participant {
    abstract val name: String
    abstract val cards: Cards
    abstract fun receiveCard(card: Card)

    open fun receiveInitialCards(cards: List<Card>) {
        require(cards.size == Deck.INITIAL_DEAL_SIZE) { "처음 받아야 할 카드 수는 ${Deck.INITIAL_DEAL_SIZE}장 입니다." }
        cards.forEach(::receiveCard)
    }
}
