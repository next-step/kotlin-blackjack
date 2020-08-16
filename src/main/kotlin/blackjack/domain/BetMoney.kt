package blackjack.domain

private val MONEY_REGEX = "(\\d{1,2})".toRegex()
private const val MIN_MONEY = 1
private const val MAX_MONEY = 100000

class BetMoney(val money: Int) {

    companion object {
        fun newInstance(moneyString: String): BetMoney? {
            if (!MONEY_REGEX.matches(moneyString)) return null
            val money = moneyString.toInt()
            if (money in MIN_MONEY..MAX_MONEY) return BetMoney(money)
            return null
        }
    }
}
