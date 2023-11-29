package blackjack.domain

import java.util.Stack

class Deck(private val cards: Stack<Card>) {
    fun draw(): Card {
        require(cards.isNotEmpty()) { "카드가 없습니다." }
        return cards.pop()
    }

    fun draw(count: Int): List<Card> {
        require(cards.size >= count) { "카드가 없습니다." }
        return (1..count).map { cards.pop() }
    }

    companion object {
        fun create(): Deck {
            val cards: List<Card> = Rank.getRankSet().values
                .flatMap { rank -> Suit.getSuitSet().values.map { suit -> Card(suit, rank) } }
                .shuffled()

            return Deck(
                Stack<Card>().apply {
                    addAll(cards)
                }
            )
        }
    }
}
