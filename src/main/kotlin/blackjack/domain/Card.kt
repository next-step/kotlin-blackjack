package blackjack.domain


data class Card(
    val pattern: Pattern,
    val number: CardNumber
) {
    override fun toString(): String {
        return "${number.value}${pattern.description}"
    }

    companion object {
        val CARDS: MutableList<Card> = CardNumber.values()
            .flatMap { number -> Pattern.values().map { pattern -> Card(pattern, number) } }
            .shuffled()
            .toMutableList()
    }
}
