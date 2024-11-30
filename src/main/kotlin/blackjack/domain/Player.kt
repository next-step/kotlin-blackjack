package blackjack.domain

class Player(private val name: PlayerName, initialCards: List<Card>) {
    private val hand =
        Hand().apply {
            require(initialCards.size == 2) { "플레이어는 시작 시 두 장의 카드를 가지고 있어야 합니다." }
            initialCards.forEach { addCard(it) }
        }

    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun displayHand(): String {
        return hand.getCards().joinToString(", ") { it.display() }
    }

    fun calculateTotal(): Int {
        return hand.calculateBestTotal()
    }

    fun getName(): String {
        return name.value
    }
}
