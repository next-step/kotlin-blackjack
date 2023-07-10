package blackjack.domain

data class FinalResult(val dealerResult: DealerResult, val playersResults: Map<Player, PlayerGameResult>)
