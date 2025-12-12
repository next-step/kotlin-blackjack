package domain

class Player(val name: String) {

    init {
        require(name.isNotBlank()) { "참가자 이름은 빈 값일 수 없습니다." }
    }
}
