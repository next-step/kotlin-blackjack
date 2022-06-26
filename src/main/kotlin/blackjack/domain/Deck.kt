package blackjack.domain

class Deck() {
    private val cards: MutableList<Card> = CardNumber.values().flatMap { cardNumber ->
        CardSuit.values().map { Card(cardNumber, it) }
    }.shuffled().toMutableList()

    fun draw(): Card {
        return cards.removeFirstOrNull() ?: throw IllegalStateException("더 이상 뽑을 패가 존재하지 않습니다.")
    }
}
