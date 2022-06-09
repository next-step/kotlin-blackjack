package blackjack.domain.blackjack

import blackjack.domain.card.Card
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Player
import blackjack.domain.score.Match
import blackjack.domain.score.Score

data class BlackJackResult(
    val playerResults: List<PlayerResult>,
    val dealerResult: DealerResult
) {

    companion object {
        fun of(players: List<Player>, dealer: Dealer): BlackJackResult {
            val matchByPlayer = players.associateWith { !dealer.match(it) }
            val playerResults = matchByPlayer.entries
                .map { PlayerResult.of(it.key, it.value) }
            val dealerResult = DealerResult.of(dealer, matchByPlayer.values.map { !it }.toList())
            return BlackJackResult(playerResults, dealerResult)
        }
    }
}

data class ParticipantResult(
    val name: String,
    val cards: List<Card>,
    val score: Score,
) {
    companion object {
        fun of(participant: Participant): ParticipantResult {
            return ParticipantResult(
                name = participant.name,
                cards = participant.cards,
                score = participant.score
            )
        }
    }
}

data class PlayerResult(
    val participantResult: ParticipantResult,
    val match: Match
) {

    companion object {
        fun of(player: Player, match: Match): PlayerResult {
            return PlayerResult(
                participantResult = ParticipantResult.of(player),
                match = match
            )
        }
    }
}

data class DealerResult(
    val participantResult: ParticipantResult,
    val matches: List<Match>
) {
    companion object {
        fun of(dealer: Dealer, matches: List<Match>): DealerResult {
            return DealerResult(
                participantResult = ParticipantResult.of(dealer),
                matches = matches
            )
        }
    }
}
