package blackjack

class Player(
    val name: String,
) {
    init {
        require(name.length <= NAME_LENGTH_LIMIT) { "플레이어 이름은 5자를 초과할 수 없다." }
    }

    companion object {
        private const val NAME_LENGTH_LIMIT = 5
    }
}
