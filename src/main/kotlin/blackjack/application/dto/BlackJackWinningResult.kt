package blackjack.application.dto

import blackjack.domain.participant.vo.Name
import blackjack.domain.participant.vo.WinningAmount

data class BlackJackWinningResult(
    val name: Name,
    val winningAmount: WinningAmount
)

data class BlackJackWinningResults(
    val blackJackWinningResults: List<BlackJackWinningResult>
)
