package blackjack.application.dto

import blackjack.domain.WinningScores
import blackjack.domain.participant.vo.Name

data class BlackJackWinningResult(
    val name: Name,
    val winningScores: WinningScores
)

data class BlackJackWinningResults(
    val blackJackWinningResults: List<BlackJackWinningResult>
)
