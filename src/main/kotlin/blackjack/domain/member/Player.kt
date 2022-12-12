package blackjack.domain.member

import blackjack.domain.Cards

class Player(
    override val name: String,
    override val cards: Cards,
) : Member {
    init {
        require(name.isNotBlank()) { "이름은 빈 값이 올 수 없어요" }
    }

    override fun ableMoreDrawCard() = !(isEqualsBlackjackNumber() || isOverBlackjackNumber())
    override fun conditionOfWin(otherMember: Member): Boolean {
        return isNearBlackJackThan(otherMember)
    }
}
