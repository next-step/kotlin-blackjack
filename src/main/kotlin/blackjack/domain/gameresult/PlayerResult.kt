package blackjack.domain.gameresult

import blackjack.domain.player.Player

data class PlayerResult(val player: Player, val profit: Int)
