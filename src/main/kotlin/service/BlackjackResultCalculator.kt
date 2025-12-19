package service

import domain.Dealer
import domain.Player

class BlackjackResultCalculator {

    enum class Outcome {
        PLAYER_BUST,
        DEALER_BUST,
        PUSH,
        PLAYER_BLACKJACK,
        DEALER_BLACKJACK,
        PLAYER_WIN,
        DEALER_WIN
        ;

        fun profitOf(bet: Int): Int = when (this) {
            // 정수형 처리를 위해 1.5배 하는 것이 아니라 3배 후 2로 나눔
            PLAYER_BLACKJACK -> (bet * 3) / 2
            PLAYER_WIN, DEALER_BUST -> bet
            PUSH -> 0
            DEALER_WIN, DEALER_BLACKJACK, PLAYER_BUST -> -bet
        }
    }

    fun outcomeOf(player: Player, dealer: Dealer): Outcome = when {
        player.isBust() -> Outcome.PLAYER_BUST
        dealer.isBust() -> Outcome.DEALER_BUST

        player.isBlackjack() && dealer.isBlackjack() -> Outcome.PUSH
        player.isBlackjack() -> Outcome.PLAYER_BLACKJACK
        dealer.isBlackjack() -> Outcome.DEALER_BLACKJACK

        player.score() > dealer.score() -> Outcome.PLAYER_WIN
        player.score() < dealer.score() -> Outcome.DEALER_WIN
        else -> Outcome.PUSH
    }


    fun profitReport(players: List<Player>, dealer: Dealer) {
        players.forEach { player ->
            val outcome = outcomeOf(player, dealer)
            player.profit = outcome.profitOf(player.bettingAmount)
        }
        dealer.profit = -players.sumOf { it.profit }
    }
}
