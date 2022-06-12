package blackjack.domain

class Player(val name: String) {
    val cards = mutableListOf<Card>()
    fun takeCard(card: Card) {
        cards.add(card)
    }

    fun needCard(answer: String): Boolean =
        when (answer) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("플레이어 상태가 이상합니다")
        }
}
