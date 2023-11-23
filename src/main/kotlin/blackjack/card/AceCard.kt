package blackjack.card

class AceCard(
    val pattern: CardPattern
) : BlackJackCard {
    override fun toString(): String {
        return "ace${pattern.patternName}"
    }
}
