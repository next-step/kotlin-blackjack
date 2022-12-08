package blackjack.domain

class Player(
    override val name: String,
    override val cards: Cards,
) : Member {
    override fun ableMoreDrawCard() = !(isEqualsBlackjackNumber() || isOverBlackjackNumber())
    override fun conditionOfWin(otherMember: Member): Boolean {
        return this.isNearBlackJackThan(otherMember)
    }

    init {
        require(name.isNotBlank()) { "이름은 빈 값이 올 수 없어요" }
    }
}
