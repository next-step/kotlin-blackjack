package blackjack.domain.result

data class PlayerResults(private val playerResults: List<PlayerResult>) : Iterable<PlayerResult> by playerResults
