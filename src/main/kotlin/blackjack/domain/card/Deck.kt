package blackjack.domain.card

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
        cards.add(Ace(type))
        cards.add(Two(type))
        cards.add(Three(type))
        cards.add(Four(type))
        cards.add(Five(type))
        cards.add(Six(type))
        cards.add(Seven(type))
        cards.add(Eight(type))
        cards.add(Nine(type))
        cards.add(Jack(type))
        cards.add(Queen(type))
        cards.add(King(type))
        return cards
    }
}
