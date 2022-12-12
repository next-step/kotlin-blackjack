package blackjack.domain

data class Bet(val money: Int) {

    init {
        require(money >= MIN) { "배팅 금액은 음수일 수 없어요" }
    }

    companion object {
        private const val MIN = 0
        private const val CACHE_MAX = 100_000
        private const val CACHE_UNIT = 1000

        private val cache = (MIN..CACHE_MAX step CACHE_UNIT).associateWith { Bet(it) }

        fun of(value: Int): Bet {
            return cache[value] ?: Bet(value)
        }
    }
}
