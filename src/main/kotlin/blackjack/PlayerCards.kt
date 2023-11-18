package blackjack

class PlayerCards(val cards: MutableList<PlayingCard> = mutableListOf()) {
    private var compound = CardsCompound.get()

    fun addCard(card: PlayingCard) {
        cards.add(card)
        compound = compound.addNumber(card.number)
    }

    fun isBusted() = compound.isBusted
    fun getBestScore() = compound.bestNumber
}
