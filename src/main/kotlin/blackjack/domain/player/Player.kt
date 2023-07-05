package blackjack.domain.player

import blackjack.domain.GameMoney

class Player(name: PlayerName, private val bettingMoney: GameMoney) : BlackJackPlayer(name) {

    var gameResultState: GameResultState = GameResultState.DRAW

    val finalIncome: Int
        get() {
            return when (gameResultState) {
                GameResultState.WIN -> getWinMoney(bettingMoney)
                GameResultState.DRAW -> MINIMUM_MONEY
                GameResultState.LOSE -> bettingMoney.money.unaryMinus()
            }
        }

    private fun getWinMoney(gameMoney: GameMoney): Int {
        val money = gameMoney.money
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

    companion object {
        const val MINIMUM_MONEY = 0
        const val BONUS_BLACKJACK = 1.5
    }
}
