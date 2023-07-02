package blackjack.domain.player

class Player(name: PlayerName) : BlackJackPlayer(name) {

    var gameResultState: GameResultState = GameResultState.DRAW
    var bettingMoney: Int = 0
        private set
        get() {
            return when (gameResultState) {
                GameResultState.WIN -> getWinMoney(field)
                GameResultState.DRAW -> field
                GameResultState.LOSE -> field.unaryMinus()
            }
        }

    fun getWinMoney(money: Int): Int {
        if (cards.isBlackJack()) {
            return (money * 1.5).toInt()
        }
        return money
    }

    fun matchGameScore(dealerScore: Int): GameResultState {
        gameResultState = cards.match(dealerScore)
        return gameResultState
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
    }
}
