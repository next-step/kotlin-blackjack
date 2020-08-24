package blackJack.domain

class Player(name: String) : Person(name) {
    var betMoney = DEFAULT_MONEY
        private set

    fun bettingMoney(money: Int) {
        betMoney = money
    }

    fun getProfit(winOrLose: WinOrLose): Int {
        return (betMoney * getPercent(winOrLose)).toInt()
    }

    private fun getPercent(winOrLose: WinOrLose): Double {
        return when (winOrLose) {
            WinOrLose.WIN -> {
                test()
            }
            WinOrLose.DRAW -> {
                DRAW_MONEY
            }
            WinOrLose.LOSE -> {
                LOSE_MONEY
            }
        }
    }

    fun test(): Double {
        if (isBlackJack()) {
            return BLACK_JACK_MONEY
        }
        return WIN_MONEY
    }

    private fun isBlackJack(): Boolean = hands.size == 2 && getTotalScore() == 21

    companion object {
        const val DEFAULT_MONEY = 0
        const val BLACK_JACK_MONEY = 1.5
        const val WIN_MONEY = 1.0
        const val DRAW_MONEY = 0.0
        const val LOSE_MONEY = -1.0
    }
}
