package blackjack.domain

private val MONEY_REGEX = "(\\d+)".toRegex()
const val MIN_MONEY = 1
const val MAX_MONEY = 100_000

class BetMoney(val money: Int) {

    companion object {
        fun newInstance(moneyString: String): BetMoney? {
            if (!MONEY_REGEX.matches(moneyString)) return null
            val money = moneyString.toInt()
            if (money in MIN_MONEY..MAX_MONEY) return BetMoney(money)
            return null
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BetMoney

        if (money != other.money) return false

        return true
    }

    override fun hashCode(): Int {
        return money
    }
}
