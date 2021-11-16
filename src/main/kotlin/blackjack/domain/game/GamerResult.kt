package blackjack.domain.game

import blackjack.domain.player.Player

data class PlayerResult(val result: GameResult.Type, val player: Player)

data class DealerResult(val win: Int, val draw: Int, val lose: Int)
