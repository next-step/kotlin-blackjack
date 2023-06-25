package blackjack.domain

class PlayingCardPack(playingCards: List<PlayingCard>) : Iterable<PlayingCard> {

    private var playingCards: MutableList<PlayingCard> = playingCards.toMutableList()

    fun shuffle() {
        this.playingCards.shuffle()
    }

    fun pick(): PlayingCard {
        return this.playingCards.removeFirst()
    }

    override fun iterator(): Iterator<PlayingCard> {
        return this.playingCards.iterator()
    }
}
