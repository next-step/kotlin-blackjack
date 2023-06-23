package blackjack

class Player(private val rule: Rule = Rule()) {
    private val _cards = mutableListOf(rule.getCard(), rule.getCard())
    val cards: List<Card> get() = _cards

    val sum: Int get() = Rule.calculateSum(cards)
}
