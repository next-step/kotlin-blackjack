package blackjack.domain.view.model

@JvmInline
value class PlayerViews(private val players: List<PlayerView>) : List<PlayerView> by players

@JvmInline
value class PlayerViewResults(private val results: List<PlayerViewResult>) : List<PlayerViewResult> by results
