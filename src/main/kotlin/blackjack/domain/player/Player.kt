package blackjack.domain.player

class Player(
    override val name: String,
) : Participant(name) {

    init {
        require(name.isNotBlank()) { "이름은 공백제외 1글자 이상이어야 합니다." }
    }
}
