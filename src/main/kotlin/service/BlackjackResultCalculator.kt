package service

import domain.BlackjackCards
import domain.Dealer
import domain.GameOutcome
import domain.GameOutcome.LOSE
import domain.GameOutcome.WIN
import domain.Player

class BlackjackResultCalculator {
    private fun determineWinStatus(
        players: Set<Player>,
        dealer: Dealer,
    ): Map<Player, GameOutcome> {
        val result = mutableMapOf<Player, GameOutcome>()

        players.forEach {
            if (dealer.score() > BlackjackCards.BLACKJACK_MAX_SCORE) {
                result[it] = WIN
            } else if (it.score() > BlackjackCards.BLACKJACK_MAX_SCORE) {
                result[it] = LOSE
            } else {
                result[it] = GameOutcome.judge(it.score() >= dealer.score())
            }
        }

        return result
    }

    /**
     * 게임 결과에 따른 수익금 계산 : (예시) profitCalculator.calculate(..)
     * - 플레이어
     *   - 추가 카드를 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게된다.
     *   - 처음 두 장의 카드 합이 21일 경우 베팅 금액의 1.5배를 딜러에게 받는다.
     * - 딜러 : 21을 초과할 경우 플레이어들은 패에 상관없이 승리해 배팅 금액을 받는다.
     * - 딜러와 플레이어가 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
     * 결과 : 플레이어, 딜러의 수익금을 출력한다.
     */
    fun profitReport(players: Set<Player>, dealer: Dealer) {
        val winStatusByPlayer = determineWinStatus(players, dealer)

        players.forEach { player ->
            val profit = when {
                player.isBust() -> -player.bettingAmount
                dealer.isBust() -> player.bettingAmount
                player.isBlackjack() && !dealer.isBlackjack() -> (player.bettingAmount * 1.5).toInt()
                player.isBlackjack() && dealer.isBlackjack() -> 0
                else -> when (winStatusByPlayer[player]) {
                    WIN -> player.bettingAmount
                    LOSE -> -player.bettingAmount
                    else -> 0
                }
            }

            player.profit = profit
        }

        dealer.profit = -players.sumOf { it.profit }
    }
}
