package blackjack.domain.cards

import blackjack.domain.card.Card
import blackjack.domain.card.Character
import blackjack.domain.card.Suit

data class DeckCards(private val _cardList: MutableList<Card>) : Cards(_cardList) {
    init {
        require(_cardList.size == INITIAL_CARD_COUNT) { "Invalid initial Card count ${_cardList.size}" }
        require(_cardList.count { it.suit == Suit.Spade } == Character.values().size) { "Invalid suit count ${Suit.Spade}" }
        require(_cardList.count { it.suit == Suit.Diamond } == Character.values().size) { "Invalid suit count ${Suit.Diamond}" }
        require(_cardList.count { it.suit == Suit.Heart } == Character.values().size) { "Invalid suit count ${Suit.Heart}" }
        require(_cardList.count { it.suit == Suit.Clover } == Character.values().size) { "Invalid suit count ${Suit.Clover}" }
        require(_cardList.toSet().size == INITIAL_CARD_COUNT) { "Duplicate cards $_cardList" }
    }
    fun shuffle() {
        _cardList.shuffle()
    }

    fun drawTop(): Card = _cardList.removeAt(0)

    companion object {
        private val INITIAL_CARD_COUNT = Suit.values().size * Character.values().size
        fun fullDeckCards() = DeckCards(fullCardList().toMutableList())
        private fun fullCardList() = Card.fullCardList()
    }
}
