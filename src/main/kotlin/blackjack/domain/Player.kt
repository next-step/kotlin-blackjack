package blackjack.domain

class Player(
    val name: String
) {
    init {
        require(name.isNotBlank()) { "플레이어 이름은 한글자 이상이여야 합니다." }
    }

    fun give(card: Card) {
        TODO("Not yet implemented")
    }
}
