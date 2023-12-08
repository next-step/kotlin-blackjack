package blackJack.card.deck

import card.PlayingCard

class FakeGameDeck(private val cardList: MutableList<PlayingCard>) {

    private var index = 0

    fun getCardWithIncrease(): PlayingCard {
        if (isMaxIndexOfCard()) resetCard()
        return cardList[index++]
    }

    private fun isMaxIndexOfCard(): Boolean {
        return index == cardList.size
    }

    private fun resetCard() {
        index = 0
    }
}
