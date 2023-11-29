package blackjack.domain

class BettingBoard(val bettings: Map<String, Int>) {
    private val total: Int

    init {
        require(bettings.isNotEmpty()) { "배팅 금액이 입력되지 않았습니다." }
        total = bettings.values.sum()
    }
}
