package blackjack.step2.domain

class Player(val name: String) {
    init {
        require(name.isNotBlank()) { "이름은 빈 값일 수 없습니다." }
    }
}
