package blackjack.domain.player

import blackjack.domain.card.CardScore

data class PlayerScore(val player: Player, val cardScore: CardScore)
