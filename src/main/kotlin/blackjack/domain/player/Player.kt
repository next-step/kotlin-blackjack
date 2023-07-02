package blackjack.domain.player

class Player(name: PlayerName) : BlackJackPlayer(name) {

    var gameResultState: GameResultState = GameResultState.DRAW

    private var bettingMoney = MINIMUM_MONEY

    val finalIncome: Int
        get() {
            return when (gameResultState) {
                GameResultState.WIN -> getWinMoney(bettingMoney)
                GameResultState.DRAW -> MINIMUM_MONEY
                GameResultState.LOSE -> bettingMoney.unaryMinus()
            }
        }

    private fun getWinMoney(money: Int): Int {
        if (cards.isBlackJack()) {
            return (money * BONUS_BLACKJACK).toInt()
        }
        return money
    }

    fun matchGameScore(dealer: Dealer) {
        gameResultState = when {
            isBust() -> GameResultState.LOSE
            dealer.isBust() -> GameResultState.WIN
            isBlackJack() && dealer.isBlackJack() -> GameResultState.DRAW
            else -> cards.match(dealer.getScore())
        }
        dealer.addEarnMoney(finalIncome.unaryMinus())
    }

    fun setBettingMoney(moneyString: String) {
        val money = moneyString.toIntOrNull()
        require(money != null) {
            "베팅 금액은 숫자이어야함"
        }

        require(money > MINIMUM_MONEY) {
            "베팅 금액은 0보다 커야함"
        }
        bettingMoney = money
    }

    companion object {
        const val MINIMUM_MONEY = 0
        const val BONUS_BLACKJACK = 1.5
    }
}
