package blackjack.domain

open class Player(val name: String, val hands: Hands) {
    init {
        require(name.isNotBlank()) { "플레이어의 이름이 비어있습니다." }
    }

    open fun hit(card: Card) {
        hands.hit(card)
    }

    fun isBust(): Boolean {
        return hands.isBust()
    }

    fun sum(): Int {
        return hands.sum()
    }
}
