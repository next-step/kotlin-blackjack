package blackjack.domain

private val MONEY_REGEX = "(\\d+)".toRegex()
const val MIN_MONEY = 1
const val MAX_MONEY = 100_000

data class BetMoney(val money: Int) {

    companion object {
        fun newInstance(moneyString: String): BetMoney? {
            if (!MONEY_REGEX.matches(moneyString)) return null
            val money = moneyString.toInt()
            if (money in MIN_MONEY..MAX_MONEY) return BetMoney(money)
            return null
        }
    }
}
