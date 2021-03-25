package blackjack.domain

import java.util.LinkedList

class Deck {
    private val cards = LinkedList(initializeCardsByCardSuit())

    fun pick(): Card {
        if (isEmpty()) throw IllegalStateException("덱에 카드가 더이상 존재하지 않습니다.")
        cards.shuffle()
        return cards.pop()
    }

    private fun isEmpty(): Boolean {
        return cards.isEmpty()
    }

    private fun initializeCardsByCardSuit(): List<Card> {
        var cardsSet: List<Card> = mutableListOf()
        CardSuit.values().forEach {
            cardsSet = initializeCardsByCardSpell(cardsSet, it)
        }
        return cardsSet
    }

    private fun initializeCardsByCardSpell(cardsSet: List<Card>, cardSuit: CardSuit): List<Card> {
        val cardsSet: MutableList<Card> = cardsSet.toMutableList()
        CardSpell.values().forEach {
            cardsSet.add(Card.of(cardSuit, it))
        }

        return cardsSet
    }
}
