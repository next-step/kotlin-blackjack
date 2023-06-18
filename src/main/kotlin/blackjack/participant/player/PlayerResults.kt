package blackjack.participant.player

@JvmInline
value class PlayerResults(private val results: List<PlayerResult>) : List<PlayerResult> by results
