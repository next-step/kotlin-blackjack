package blackjack.domain

import blackjack.model.Bet

class GamePlayer(
    override val name: String,
    override val bet: Bet,
    override val play: Play = GamePlay(),
) : Player {
    init {
        require(name.isNotBlank()) { "Player 이름은 공백이 될 수 없습니다. 유효한 Player 명을 입력해주세요." }
    }
}
