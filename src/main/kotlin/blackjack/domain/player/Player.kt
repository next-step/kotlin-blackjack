package blackjack.domain.player

class Player(userName: UserName) : User(userName) {
    override fun canDraw() = hands.calculateScore().isLowerThan(PLAYER_DRAW_CONDITION)

    companion object {
        private const val PLAYER_DRAW_CONDITION = 21
    }
}
