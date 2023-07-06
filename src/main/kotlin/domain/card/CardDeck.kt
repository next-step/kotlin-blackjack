package domain.card

interface CardDeck {
    fun pop(): Card

    companion object {
        fun createNewDeck(): List<Card> {
            return Card.createSpades()
                .plus(Card.createHearts())
                .plus(Card.createDiamonds())
                .plus(Card.createClovers())
        }
    }
}
