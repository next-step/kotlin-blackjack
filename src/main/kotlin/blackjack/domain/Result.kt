package blackjack.domain

import blackjack.domain.player.Player

data class Result(val elements: Map<Player, Money>)
