package blackjack.domain.view.model.view

@JvmInline
value class ParticipantViews(private val players: List<ParticipantView>) : List<ParticipantView> by players

@JvmInline
value class ParticipantViewResults(
    private val results: List<ParticipantViewResult>,
) : List<ParticipantViewResult> by results
