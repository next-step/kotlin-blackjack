package blackjack.domain.game

import blackjack.domain.player.Players

data class GameResult(val scoreResult: ScoreResult, val players: Players)
