package blackjack.domain.batting

import blackjack.domain.player.PlayerName

sealed interface PlayerBet {
    val playerName: PlayerName

    data class BetPlaced(
        override val playerName: PlayerName,
        val betAmount: Amount,
    ) : PlayerBet

    data class BetFinished(
        override val playerName: PlayerName,
        val betAmount: Amount,
        val resultAmount: Amount,
    ) : PlayerBet
}
