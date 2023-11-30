package blackjack.domain.game

import blackjack.domain.player.PlayerScore

data class BlackJackGameResult(val playerScores: List<PlayerScore>)
