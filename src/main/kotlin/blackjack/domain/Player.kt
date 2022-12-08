package blackjack.domain

import blackjack.domain.Const.BLACKJACK_NUMBER

class Player(
    val name: String,
    override val cards: Cards,
) : Member {
    override fun ableMoreDrawCard() = resultScore() < BLACKJACK_NUMBER

    init {
        require(name.isNotBlank()) { "이름은 빈 값이 올 수 없어요" }
    }
}
