package blackjack.card

class NormalCard(
    val number: Int,
    val pattern: CardPattern
) : BlackJackCard {
    override fun toString(): String {
        return "${number}${pattern.patternName}"
    }
}
