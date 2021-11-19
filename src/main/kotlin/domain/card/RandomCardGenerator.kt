package domain.card

class RandomCardGenerator : CardGenerator {
    private val cards: MutableList<PlayingCard> = PlayingCard.createMutableList()
    override fun getCard(): PlayingCard = cards.removeAt(cards.indices.random())
}
