package blackjack.domain

class GamePlayer(
    override val name: String,
    override val play: Play = GamePlay(),
) : Player {
    init {
        require(name.isNotBlank()) { "Player 이름은 공백이 될 수 없습니다. 유효한 Player 명을 입력해주세요." }
    }
}
