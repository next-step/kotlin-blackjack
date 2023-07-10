package blackjack.dto

import blackjack.domain.gamer.Player

data class GeneratePlayerRequest(val playerName: String, val bettingMoney: Int) {
    init {
        require(bettingMoney > 0) { " 베팅 금액이 음수가 되면 안됩니다." }
    }

    fun generatePlayer(): Player {
        return Player.generatePlayer(this)
    }
}
