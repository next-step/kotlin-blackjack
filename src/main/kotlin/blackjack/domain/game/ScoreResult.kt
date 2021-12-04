package blackjack.domain.game

import blackjack.domain.player.Player

data class ScoreResult(val score: Map<Player, List<Score>>)
