package blackjack.application.dto

import blackjack.domain.participant.vo.Name
import blackjack.domain.participant.vo.WinningScores

data class BlackJackWinningResult(
    val name: Name,
    val winningScores: WinningScores
) 

data class BlackJackWinningResults(
    val blackJackWinningResults: List<BlackJackWinningResult>
)
