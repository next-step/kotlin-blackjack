package blackjack

open class Player(
    val info: PlayerInfo = PlayerInfo(),
    val cards: Cards = Cards()
) {

    open fun canGetCard(): Boolean {
        return cards.sum < Rule.BLACK_JACK
    }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun win() = info.record.win()

    fun lose() = info.record.lose()
}
