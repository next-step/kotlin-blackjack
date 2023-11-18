package blackjack.domain

open class Player(val name: String) {
    init {
        require(name.isNotBlank()) { "플레이어 이름은 빈 값일 수 없습니다." }
    }
}
