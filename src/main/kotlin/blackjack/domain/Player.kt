package blackjack.domain

class Player(name: String) {
    init {
        require(name.isNotBlank()) { "플레이어의 이름이 비어있습니다." }
    }
}
