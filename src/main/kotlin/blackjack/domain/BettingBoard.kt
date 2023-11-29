package blackjack.domain

class BettingBoard(private val bettings: Map<String, Int>) {
    private var total: Int

    init {
        require(bettings.isNotEmpty()) { "배팅 금액이 입력되지 않았습니다." }
        total = bettings.values.sum()
    }

    fun betOf(name: String): Int {
        return bettings[name] ?: throw IllegalArgumentException("존재하지 않는 플레이어입니다.")
    }

    fun adjustment(name: String, result: Result, isBlackjack: Boolean): Int {
        val betting = bettings[name] ?: throw IllegalArgumentException("존재하지 않는 플레이어입니다.")

        var prize = when (result) {
            Result.WIN -> betting * 2
            Result.DRAW -> betting
            Result.LOSE -> 0
        }

        if (isBlackjack) {
            prize = (betting * 2.5).toInt()
        }

        total -= prize
        return prize
    }

    fun getRemain(): Int = total
}
