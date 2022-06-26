package camp.nextstep.blackjack.player

class Gambler(override val name: String) : Player {

    override val hand = Hand()

    init {
        require(name.isNotBlank()) { "올바른 플레이어 이름을 입력해주세요. ($name)" }
    }
}
