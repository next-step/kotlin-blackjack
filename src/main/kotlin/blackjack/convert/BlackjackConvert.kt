package blackjack.convert

import blackjack.domain.card.PlayingCards
import blackjack.domain.game.result.MatchResults
import blackjack.domain.game.result.ParticipantPlayResults
import blackjack.domain.participant.Participant
import blackjack.domain.participant.PlayerInfo
import blackjack.domain.participant.Players
import blackjack.domain.view.model.input.PlayersBetInput
import blackjack.domain.view.model.view.CardView
import blackjack.domain.view.model.view.MatchResultView
import blackjack.domain.view.model.view.MatchResultViews
import blackjack.domain.view.model.view.ParticipantView
import blackjack.domain.view.model.view.ParticipantViewResult
import blackjack.domain.view.model.view.ParticipantViewResults
import blackjack.domain.view.model.view.ParticipantViews

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

fun PlayersBetInput.convertDomain(): List<PlayerInfo> = this.map {
    PlayerInfo(name = it.name, betAmount = it.bettingAmount)
}
