package blackjack.domain

import java.util.Stack

class CardDeck private constructor(val cards: Stack<Card>) {

    fun draw(): Card {
        check(cards.isNotEmpty()) { "뽑을 카드가 없습니다." }

        return cards.pop()
    }

    fun hit(card: Card): Card = cards.push(card.copy())

    companion object {

        fun empty(): CardDeck = CardDeck(Stack<Card>())
        fun shuffle(): CardDeck = CardDeck(init())

        private fun init() = Card.Suit.values()
            .flatMap { suit ->
                Card.Number.values().map { number ->
                    Card(suit, number)
                }
            }
            .shuffled()
            .toCollection(Stack())
    }
}
