package domain.card

class MockedCardGenerator : CardGenerator {
    private val cards = PlayingCard.createMutableList()
    override fun getCard() = cards.removeLast()
}
