package blackjack.domain.card

import blackjack.model.Card
import blackjack.model.Character
import blackjack.model.Suit

data class Cards(private val _cardList: MutableList<Card>) {
    val cardList get() = _cardList.toList()
    val size get() = _cardList.size

    fun shuffle() {
        _cardList.shuffle()
    }

    fun drawTop(): Card = _cardList.removeAt(0)

    fun add(card: Card) {
        _cardList.add(card)
    }

    companion object {
        fun fullCards(): Cards {
            val mutableList = mutableListOf<Card>()
            Suit.values().forEach { suit ->
                Character.values().forEach { character ->
                    mutableList.add(Card(suit, character))
                }
            }
            return Cards(mutableList)
        }
    }

    override fun toString(): String = _cardList.toString()
}
