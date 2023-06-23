package blackjack

class Player(private val round: Round = Round()) {
    private val _cards = mutableListOf(round.getCard(), round.getCard())
    val cards: List<Card> get() = _cards

    val sum: Int get() = Round.calculateSum(cards)
}
