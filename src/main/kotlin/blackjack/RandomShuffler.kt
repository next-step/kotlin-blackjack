package blackjack

class RandomShuffler : Shuffler {
    override fun shuffle(cards: MutableList<Card>) {
        cards.shuffle()
    }
}
