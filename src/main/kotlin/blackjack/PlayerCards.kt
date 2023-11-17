package blackjack

class PlayerCards(private val cards: MutableList<PlayingCard> = mutableListOf()) {
    private val compound = CardsCompound.get()

    fun addCard(card: PlayingCard) {
        cards.add(card)
        compound.addNumber(card.number)
    }

    fun isBusted() = compound.isBusted
    fun getBestScore() = compound.bestNumber

    override fun toString(): String {
        return cards.joinToString { it.toString() }
    }
}
