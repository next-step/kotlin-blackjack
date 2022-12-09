package blackjack.domain.member

import blackjack.domain.Cards

sealed class Player(
    final override val name: String,
    override val cards: Cards,
) : Member {
    init {
        require(name.isNotBlank()) { "이름은 빈 값이 올 수 없어요" }
    }

    override fun ableMoreDrawCard() = !(isEqualsBlackjackNumber() || isOverBlackjackNumber())
    override fun conditionOfWin(otherMember: Member): Boolean {
        return this.isNearBlackJackThan(otherMember)
    }

    fun toResultPlayer(dealer: Dealer): Player {
        if (dealer.isOverBlackjackNumber()) {
            return WinPlayer.init(this)
        }

        return if (isWin(dealer)) {
            WinPlayer.init(this)
        } else {
            LosePlayer.init(this)
        }
    }
}
