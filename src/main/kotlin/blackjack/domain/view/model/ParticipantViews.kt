package blackjack.domain.view.model

@JvmInline
value class ParticipantViews(private val players: List<ParticipantView>) : List<ParticipantView> by players

@JvmInline
value class ParticipantViewResults(private val results: List<ParticipantViewResult>) : List<ParticipantViewResult> by results
