package blackJack.domain

class Player(name: String) : People(name) {
    init {
        require(!name.isBlank()) { "이름은 없을수 받을수 없습니다." }
    }
}
