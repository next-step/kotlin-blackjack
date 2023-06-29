package blackjack.model

import blackjack.model.participant.BlackjackPlayer

data class BlackjackPlayerResult(val player: BlackjackPlayer, val revenue: Money)
