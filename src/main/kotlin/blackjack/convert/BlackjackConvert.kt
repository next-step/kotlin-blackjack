package blackjack.convert

import blackjack.domain.card.PlayingCards
import blackjack.domain.game.result.MatchResults
import blackjack.domain.game.result.ParticipantPlayResults
import blackjack.domain.view.model.CardView
import blackjack.domain.view.model.MatchResultView
import blackjack.domain.view.model.MatchResultViews
import blackjack.domain.view.model.PlayerView
import blackjack.domain.view.model.PlayerViewResult
import blackjack.domain.view.model.PlayerViewResults
import blackjack.domain.view.model.PlayerViews
import blackjack.participant.Player
import blackjack.participant.Players

fun Players.convertView(): PlayerViews = this.map { it.convertView() }
    .run(::PlayerViews)

fun Player.convertView(): PlayerView = PlayerView(
    name = this.getName(),
    cards = this.getCards().cardViews()
)

fun ParticipantPlayResults.convertView(): PlayerViewResults = this.participantPlayResults.map {
    val player = it.participant

    PlayerViewResult(
        name = player.getName(),
        cards = player.getCards().cardViews(),
        score = it.finishState.resultScore(),
    )
}.run(::PlayerViewResults)

private fun PlayingCards.cardViews() = this.map {
    CardView(
        denomination = it.denomination.exposeName,
        suit = it.suit.exposeName,
    )
}

fun MatchResults.convertView(): MatchResultViews = this.map {
    MatchResultView(
        participantName = it.participant.getName(),
        winScore = it.winScore,
        loseScore = it.loseScore,
    )
}.run(::MatchResultViews)
