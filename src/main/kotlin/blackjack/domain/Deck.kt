package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape

class Deck private constructor(private val cards: MutableList<Card>) {

    fun drawCard(): Card {
        check(cards.isNotEmpty()) { "덱이 비어있습니다." }
        return cards.removeLast()
    }

    fun drawCards(count: Int): List<Card> {
        require(count <= allCards.size) { "뽑으려는 카드의 수가 ${allCards.size}를 초과할 수 없습니다." }
        return List(count) { drawCard() }
    }

    fun size(): Int = cards.size

    companion object {
        private val allCards = createCards()
        private fun createCards(): List<Card> {
            val cardNumbers = CardNumber.values()
            val cardShapes = CardShape.values()
            return cardNumbers.flatMap { number ->
                cardShapes.map { shape ->
                    Card(number, shape)
                }
            }
        }

        fun create(): Deck {
            val shuffledCards = allCards.shuffled().toMutableList()
            return Deck(shuffledCards)
        }
    }
}
