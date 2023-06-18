package blackjack.domain.view.model

@JvmInline
value class MatchResultViews(private val results: List<MatchResultView>) : List<MatchResultView> by results
