package blackjack.domain.player

@JvmInline
value class PlayerResults(private val results: List<PlayerResult>) : List<PlayerResult> by results
