package blackJack.domain

class Player(name: String) : Person(name) {
    var betMoney = DEFAULT_MONEY
        private set

    override fun addCard(card: Card) {
        if (isBust()) {
            throw IllegalArgumentException("카드의 총합이 21점을 넘겼기 때문에 카드를 더 받을수 없습니다.")
        }
        super.addCard(card)
    }

    fun bettingMoney(money: Int) {
        betMoney = money
    }

    fun getProfit(winOrLose: WinOrLose): Int {
        return (betMoney * getPercent(winOrLose)).toInt()
    }

    private fun getPercent(winOrLose: WinOrLose): Double {
        return when (winOrLose) {
            WinOrLose.WIN -> {
                getWinPercent()
            }
            WinOrLose.DRAW -> {
                DRAW_MONEY
            }
            WinOrLose.LOSE -> {
                LOSE_MONEY
            }
        }
    }

    private fun getWinPercent(): Double {
        if (isBlackJack()) {
            return BLACK_JACK_MONEY
        }
        return WIN_MONEY
    }

    private fun isBlackJack(): Boolean = hands.size == BLACK_JACK_HANDS && getTotalScore() == BLACK_JACK_SCORE

    companion object {
        private const val DEFAULT_MONEY = 0
        private const val BLACK_JACK_MONEY = 1.5
        private const val WIN_MONEY = 1.0
        private const val DRAW_MONEY = 0.0
        private const val LOSE_MONEY = -1.0
        private const val BLACK_JACK_HANDS = 2
        private const val BLACK_JACK_SCORE = 21
    }
}
