package blackjack.domain.score

import blackjack.domain.player.PlayerResult

class Scores private constructor(val values: List<Score>) {
    companion object {
        fun of(dealer: PlayerResult, players: List<PlayerResult>): Scores = Scores(
            players.map { Score(it.player.name, ScoreCalculator.calculate(dealer, it)) }
        )
    }
}
