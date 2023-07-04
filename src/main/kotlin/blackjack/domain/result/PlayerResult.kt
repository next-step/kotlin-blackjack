package blackjack.domain.result

import blackjack.domain.user.Player

data class PlayerResult(val player: Player, val profit: Int)
