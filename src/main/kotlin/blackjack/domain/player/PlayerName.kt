package blackjack.domain.player

import blackjack.domain.game.Money

@JvmInline
value class PlayerName(val value: String) {

    init {
        require(value.isNotEmpty()) { "이름은 공백일 수 없습니다." }
    }

    companion object {

        fun from(names: List<String>): List<PlayerName> {
            return names.map { PlayerName(it) }
        }
    }
}

data class PlayerNameWithMoney(val name: PlayerName, val money: Money) {

    companion object {
        fun from(names: List<PlayerName>, moneys: List<Money>): List<PlayerNameWithMoney> {
            require(names.size == moneys.size) { "이름과 돈의 크기가 같아야합니다." }
            return (names zip moneys).map { PlayerNameWithMoney(it.first, it.second) }
        }
    }
}
