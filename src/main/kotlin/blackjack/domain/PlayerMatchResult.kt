package blackjack.domain

internal data class PlayerMatchResult(val dealerResults: List<MatchResult>, val playerResult: Map<Player, MatchResult>)
