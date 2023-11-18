package blackjack.domain

open class Player(val name: String) {
    private val cards = mutableListOf<Card>()

    init {
        require(name.isNotBlank()) { "플레이어 이름은 빈 값일 수 없습니다." }
    }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getCards(): List<Card> {
        return cards.toList()
    }
}
