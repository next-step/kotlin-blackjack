package blackjack.domain

data class Deck(private val cards: MutableList<Card>) {
    fun draw(): Card {
        check(cards.isNotEmpty()) { "카드가 모두 소진되었습니다" }
        return cards.removeFirst()
    }

    companion object {
        fun create(): Deck {
            return Deck(Card.ALL.shuffled().toMutableList())
        }
    }
}
