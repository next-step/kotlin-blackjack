package blackjack.domain

class PlayingCardPack(playingCards: List<PlayingCard>) {

    private val playingCards: MutableList<PlayingCard> = playingCards.toMutableList()

    fun shuffle() {
        this.playingCards.shuffle()
    }

    fun pick(): PlayingCard {
        return this.playingCards.removeFirst()
    }

    fun remainCount(): Int = this.playingCards.size
}
