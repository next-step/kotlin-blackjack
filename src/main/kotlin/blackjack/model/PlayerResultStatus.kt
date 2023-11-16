package blackjack.model

import blackjack.dto.Money

enum class PlayerResultStatus {
    BLACKJACK,
    WIN,
    TIE,
    LOSE;

    companion object {
        fun getPrice(player: Player): Money =
            when (player.gameResult?.playerResultStatus) {
                BLACKJACK -> player.bettingMoney * 1.5
                WIN -> player.bettingMoney
                TIE -> Money.ZERO
                LOSE -> player.bettingMoney * -1
                else -> throw IllegalStateException("이상한 게임 결과 입니다.")
            }
    }
}
