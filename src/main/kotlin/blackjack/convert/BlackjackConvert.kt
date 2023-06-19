package blackjack.convert

import blackjack.domain.card.PlayingCards
import blackjack.domain.game.result.MatchResults
import blackjack.domain.game.result.ParticipantPlayResults
import blackjack.domain.view.model.CardView
import blackjack.domain.view.model.MatchResultView
import blackjack.domain.view.model.MatchResultViews
import blackjack.domain.view.model.ParticipantView
import blackjack.domain.view.model.ParticipantViewResult
import blackjack.domain.view.model.ParticipantViewResults
import blackjack.domain.view.model.ParticipantViews
import blackjack.participant.Participant
import blackjack.participant.Players

fun Players.convertView(): ParticipantViews = this.map { it.convertView() }
    .run(::ParticipantViews)

fun Participant.convertView(): ParticipantView = ParticipantView(
    name = this.getName(),
    cards = this.getCards().cardViews()
)

fun ParticipantPlayResults.convertView(): ParticipantViewResults = this.participantPlayResults.map {
    val player = it.participant

    ParticipantViewResult(
        name = player.getName(),
        cards = player.getCards().cardViews(),
        score = it.finishState.resultScore(),
    )
}.run(::ParticipantViewResults)

private fun PlayingCards.cardViews(): List<CardView> = this.map {
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
