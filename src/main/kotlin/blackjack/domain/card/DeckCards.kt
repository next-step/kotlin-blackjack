package blackjack.domain.card

import blackjack.model.Card

data class DeckCards(private val _cardList: MutableList<Card>) : Cards(_cardList) {
    fun shuffle() {
        _cardList.shuffle()
    }

    fun drawTop(): Card = _cardList.removeAt(0)

    companion object {
        fun fullDeckCards() = DeckCards(fullCardList().toMutableList())
        private fun fullCardList() = Cards.fullCardList()
    }
}
