package blackjack.domain.card

class RandomShuffleStrategy : ShuffleStrategy {
    override fun shuffle(cards: List<Card>): List<Card> {
        return cards.shuffled()
    }
}
