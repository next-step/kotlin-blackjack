package blackjack

import blackjack.CardsCompound.Companion.BEST

class PlayerCards(val cards: MutableList<PlayingCard> = mutableListOf()) {
    private var compound = CardsCompound.get()

    fun addCard(card: PlayingCard) {
        cards.add(card)
        compound = compound.addNumber(card.number)
    }

    fun isBlackjack() = getBestScore() == BEST && cards.size == 2

    fun isBusted() = compound.bestNumber > BEST
    fun getBestScore() = compound.bestNumber
}
