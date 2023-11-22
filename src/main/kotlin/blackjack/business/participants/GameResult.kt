package blackjack.business.participants

data class GameResult(val dealerResult: PlayerResult, val playerResults: List<PlayerResult>)
