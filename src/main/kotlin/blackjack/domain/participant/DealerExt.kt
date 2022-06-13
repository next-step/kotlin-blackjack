package blackjack.domain.participant

import blackjack.application.dto.BlackJackScore
import blackjack.application.dto.BlackJackStatus
import blackjack.application.dto.BlackJackWinningResult

fun Dealer.status(): BlackJackStatus = BlackJackStatus(this.name, listOf(this.cardsInHand.cards.first()))

fun Dealer.blackJackScore(): BlackJackScore =
    BlackJackScore(this.name, this.cardsInHand.cards, this.cardsInHand.calculateScore())

fun Dealer.winningResult(): BlackJackWinningResult =
    BlackJackWinningResult(this.name, this.winningScores)
