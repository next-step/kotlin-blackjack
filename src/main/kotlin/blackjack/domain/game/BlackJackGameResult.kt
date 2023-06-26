package blackjack.domain.game

data class BlackJackGameResult(
    val dealerGameResult: DelayerGameResult,
    val playerGameResults: List<PlayerGameResult>,
)
