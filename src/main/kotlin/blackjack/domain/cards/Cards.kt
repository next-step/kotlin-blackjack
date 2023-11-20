package blackjack.domain.cards

import blackjack.domain.card.Card
import blackjack.domain.card.Character
import blackjack.domain.card.Suit

abstract class Cards(private val _cardList: MutableList<Card>) {
    val cardList get() = _cardList.toList()
    val size get() = _cardList.size

    fun add(card: Card) {
        _cardList.add(card)
    }

    override fun toString(): String = _cardList.toString()

    companion object {
        @JvmStatic
        protected fun fullCardList(): List<Card> {
            val mutableList = mutableListOf<Card>()
            Suit.values().forEach { suit ->
                Character.values().forEach { character ->
                    mutableList.add(Card(suit, character))
                }
            }
            return mutableList.toList()
        }
    }
}
