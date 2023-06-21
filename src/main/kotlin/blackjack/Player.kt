package blackjack

class Player(
    val name: String,
) {
    init {
        require(name.length <= 5) { "플레이어 이름은 5자를 초과할 수 없다." }
    }
}
