package blackjack.model.card

class PlayingCards private constructor(cardList: List<Card>) : Cards(cardList), Iterator<Card> {

    private var offset = 0

    override fun hasNext() = this.offset < this.size

    override fun next() = this[this.offset++]

    companion object {
        fun createNew() = PlayingCards(shuffled)
    }
}
