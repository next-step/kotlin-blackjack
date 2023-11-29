package card

class Cards(private val cards: List<PlayingCard>) {
    private var index = 0

    fun getCard(): PlayingCard {
        return cards[index++]
    }
}
