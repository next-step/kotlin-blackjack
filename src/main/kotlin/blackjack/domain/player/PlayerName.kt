package blackjack.domain.player

class PlayerName private constructor (val value: String) {
    companion object {
        const val MAX_NAME_SIZE = 10
        fun from(value: String): PlayerName {
            require(value.length <= MAX_NAME_SIZE) { println("이름은 최대 $MAX_NAME_SIZE 글자 입니다.") }
            return PlayerName(value)
        }
    }
}
