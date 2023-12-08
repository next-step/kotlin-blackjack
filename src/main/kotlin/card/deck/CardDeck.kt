package card.deck

import card.PlayingCard

class CardDeck(private val cardList: MutableList<PlayingCard>) {

    init {
        cardList.shuffle()
    }

    private var index = 0

    fun getCardWithIncrease(): PlayingCard {
        if (isMaxIndexOfCard()) resetCard()
        return cardList[index++]
    }

    private fun isMaxIndexOfCard(): Boolean {
        return index == cardList.size
    }

    private fun resetCard() {
        cardList.shuffle()
        index = 0
    }
}
