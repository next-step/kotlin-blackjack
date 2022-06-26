package blackjack.domain.player

private val NAME_LENGTH_RANGE = 2..5

class Participant(_name: String) {
    val name = _name.trim()

    init {
        require(name.length in NAME_LENGTH_RANGE) { "이름의 길이는 ${NAME_LENGTH_RANGE}자 사이여야 합니다." }
    }
}
