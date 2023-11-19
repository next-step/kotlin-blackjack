package blackjack

class RandomShuffleStrategy : ShuffleStrategy {
    override fun shuffle(cards: List<PlayingCard>): List<PlayingCard> {
        return cards.shuffled()
    }
}
