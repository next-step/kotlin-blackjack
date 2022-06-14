package blackjack.domain.participant.player

import blackjack.application.dto.BlackJackScore
import blackjack.application.dto.BlackJackStatus
import blackjack.application.dto.BlackJackWinningResult

fun Players.statuses(): List<BlackJackStatus> =
    players.map { BlackJackStatus(it.name, it.cardsInHand.cards) }

fun Players.blackJackScores(): List<BlackJackScore> {
    return players.map { BlackJackScore(it.name, it.cardsInHand.cards, it.cardsInHand.calculateScore()) }
}

fun Players.winningResults(): List<BlackJackWinningResult> =
    players.map { BlackJackWinningResult(it.name, it.winningAmount) }
