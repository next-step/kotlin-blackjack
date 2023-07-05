package blackjack.domain

import blackjack.domain.player.Player

@JvmInline
value class GameMoney(val money: Int) {
    companion object {
        fun of(moneyString: String) : GameMoney {
            val money = moneyString.toIntOrNull()

            require(money != null) {
                "베팅 금액은 숫자이어야함"
            }

            require(money > Player.MINIMUM_MONEY) {
                "베팅 금액은 0보다 커야함"
            }
            return GameMoney(money)
        }
    }

}
