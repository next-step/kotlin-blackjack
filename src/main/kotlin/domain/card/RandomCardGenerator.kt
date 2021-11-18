package domain.card

class RandomCardGenerator : CardGenerator {
    private val cards = PlayingCard.createMutableList()
    override fun getCard() = cards.removeAt(cards.indices.random())
}
