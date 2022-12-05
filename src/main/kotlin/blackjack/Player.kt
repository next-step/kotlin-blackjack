package blackjack

class Player(
    val name: String,
    val cards: Cards
) {

    init {
        require(name.isNotBlank()) { "이름은 빈 값이 올 수 없어요" }
    }
}
