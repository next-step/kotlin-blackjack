package blackjack.domain.card

class Deck(private val cards: MutableList<Card> = Card.ALL.shuffled().toMutableList()) {
    fun draw(): Card {
        check(cards.isNotEmpty()) { "카드가 모두 소진되었습니다" }
        return cards.removeFirst()
    }
}
