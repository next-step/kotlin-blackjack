package blackJack.domain

class Player(name: String) : Person(name) {
    init {
        require(name.isNotBlank()) { "이름은 없을수 받을수 없습니다." }
    }
}
