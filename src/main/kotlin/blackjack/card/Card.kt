package blackjack.card

class Card(
    val number: CardNumber,
    val pattern: CardPattern,
) {
    override fun toString(): String {
        return "${number.displayName}${pattern.pattern}"
    }
}
