package blackjack.domain

import blackjack.domain.state.Hit
import blackjack.domain.state.State

class Player(
    val name: String,
    state: State = Hit(Hand()),
) : User(state) {

    init {
        require(name.isNotBlank()) { "플레이어의 이름은 공백일 수 없습니다." }
    }
}
