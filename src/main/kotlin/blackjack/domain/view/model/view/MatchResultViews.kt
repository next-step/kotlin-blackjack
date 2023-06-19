package blackjack.domain.view.model.view

@JvmInline
value class MatchResultViews(private val results: List<MatchResultView>) : List<MatchResultView> by results
