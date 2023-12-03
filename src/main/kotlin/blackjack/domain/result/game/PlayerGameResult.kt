package blackjack.domain.result.game

import blackjack.domain.batting.BetBoard
import blackjack.domain.card.Hand
import blackjack.domain.card.HandScore
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName

data class PlayerGameResult(
    val player: Player,
    val profit: Profit,
) {

    val name: PlayerName = player.name

    val hand: Hand = player.hand

    val score: HandScore = player.score

    companion object {
        fun of(player: Player, betBoard: BetBoard): PlayerGameResult =
            PlayerGameResult(
                player,
                betBoard.playerProfit(player.name)
            )
    }
}
