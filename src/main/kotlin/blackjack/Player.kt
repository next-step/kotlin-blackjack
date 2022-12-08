package blackjack

class Player(val name: String, val allowed: () -> String) {
    var takeCards = emptyList<Card>()
        private set

    fun takeCard(card: Card) {
        takeCards = takeCards.toMutableList().apply {
            add(card)
        }
    }
}
