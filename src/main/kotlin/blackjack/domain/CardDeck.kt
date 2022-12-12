package blackjack.domain

import java.util.Stack

class CardDeck private constructor(val cards: Stack<Card>) {

    fun draw(): Card {
        check(cards.isNotEmpty()) { "뽑을 카드가 없습니다." }

        return cards.pop()
    }

    companion object {
        fun shuffle(): CardDeck = CardDeck(init())

        private fun init() = Suit.values()
            .flatMap { suit ->
                Number.values().map { number ->
                    Card(suit, number)
                }
            }
            .shuffled()
            .toCollection(Stack())
    }
}
