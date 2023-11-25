package blackjack.domain

import blackjack.domain.state.Hit

class Player(
    val name: String,
    hand: Hand = Hand(),
) : User(Hit(hand)) {

    init {
        require(name.isNotBlank()) { "플레이어의 이름은 공백일 수 없습니다." }
    }

    override fun hit(card: Card) {
        state = state.draw(card)
    }
}
