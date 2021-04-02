package blackjack.domain.player

import blackjack.domain.BettingMoney

class Player(userName: UserName, val bettingMoney: BettingMoney) : User(userName) {
    override fun canDraw() = hands.calculateScore().isLowerThan(PLAYER_DRAW_CONDITION)

    companion object {
        private const val PLAYER_DRAW_CONDITION = 21
    }
}
