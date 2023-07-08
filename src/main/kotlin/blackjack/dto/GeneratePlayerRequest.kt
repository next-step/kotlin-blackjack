package blackjack.dto

data class GeneratePlayerRequest(val playerName: String, val bettingMoney: Int) {
    init {
        require(bettingMoney > 0) { " 베팅 금액이 음수가 되면 안됩니다." }
    }
}
