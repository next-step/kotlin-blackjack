package blackjack

class Player(val name: String = "", round: Round = Round()) {
    private val _cards = mutableListOf(round.getCard(), round.getCard())
    val cards: List<Card> get() = _cards

    val sum: Int get() = PointCalculator.sum(cards)

    fun canGetCard(): Boolean {
        return sum < Round.BLACK_JACK
    }

    fun addCard(card: Card) {
        _cards.add(card)
    }
}
