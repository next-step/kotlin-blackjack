package blackJack.domain.player

import java.math.BigDecimal

class Player(
    val name: String,
    val status: State = StateImpl.of(),
    val bettingMoney: BettingMoney = BettingMoney()
) : State by status {

    init {
        require(name.isNotEmpty()) { IS_PLAYER_NAME_BLACK }
    }

    fun betting(playerMoney: BigDecimal) {
        bettingMoney.inputMoney(playerMoney)
    }

    companion object {
        fun of(playerName: String): Player {
            return Player(playerName)
        }

        private const val IS_PLAYER_NAME_BLACK = "해당 플레이어 이름이 빈값입니다."
    }
}
