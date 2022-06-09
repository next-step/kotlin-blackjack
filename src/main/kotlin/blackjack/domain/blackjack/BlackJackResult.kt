package blackjack.domain.blackjack

import blackjack.domain.card.Card
import blackjack.domain.player.Player
import blackjack.domain.score.Score

data class BlackJackResult(
    val playerResults: List<PlayerResult>
) {

    companion object {
        fun of(players: List<Player>): BlackJackResult {
            return BlackJackResult(players.map(PlayerResult.Companion::of))
        }
    }
}

data class PlayerResult(
    val playerName: String,
    val cards: List<Card>,
    val score: Score
) {

    companion object {
        fun of(player: Player): PlayerResult {
            return PlayerResult(
                playerName = player.name,
                cards = player.cards,
                score = player.score
            )
        }
    }
}
