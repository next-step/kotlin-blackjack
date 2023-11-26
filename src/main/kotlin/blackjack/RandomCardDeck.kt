package blackjack

class RandomCardDeck: CardDeck {
    override fun drawCard(): Card {
        return Card.values().random()
    }
}