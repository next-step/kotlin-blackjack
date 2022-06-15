package blackjack.domain

import blackjack.constant.ErrorMessages

/**
 * 덱에 있는 카드를 갖고 있는 클래스.
 * Created by Jaesungchi on 2022.06.15..
 */
class Deck {
    private val cards: MutableList<Card>

    init {
        cards = CardType.values().map {
            addCardToAllNumberByType(it)
        }.flatten().toMutableList()
    }

    fun takeCards(size: Int): List<Card> {
        return List(size) { takeCard() }
    }

    fun takeCard(): Card {
        require(cards.isNotEmpty()) { ErrorMessages.EMPTY_DECK }
        val index = (0 until cards.size).random()
        return cards.removeAt(index)
    }

    private fun addCardToAllNumberByType(type: CardType): List<Card> {
        val cards = mutableListOf<Card>()
        cards.add(ACE(type))
        cards.add(TWO(type))
        cards.add(THREE(type))
        cards.add(FOUR(type))
        cards.add(FIVE(type))
        cards.add(SIX(type))
        cards.add(SEVEN(type))
        cards.add(EIGHT(type))
        cards.add(NINE(type))
        cards.add(JACK(type))
        cards.add(QUEEN(type))
        cards.add(KING(type))
        return cards
    }
}
