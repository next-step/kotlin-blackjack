package blackjack

class PlayingCardPack(private val playingCards: List<PlayingCard>) :
    Iterable<PlayingCard> by playingCards {

    fun shuffle(): PlayingCardPack {
        return PlayingCardPack(this.playingCards.shuffled())
    }
}
