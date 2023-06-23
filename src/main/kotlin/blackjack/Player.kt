package blackjack

class Player(round: Round = Round()) {
    private val _cards = mutableListOf(round.getCard(), round.getCard())
    val cards: List<Card> get() = _cards

    val sum: Int get() = CardCalculator.sum(cards)

    fun canGetCard(): Boolean {
        return sum < Round.TWENTY_ONE
    }

    fun addCard(card: Card) {
        _cards.add(card)
    }
}
