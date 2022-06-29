package blackjack.domain.judge

import blackjack.domain.blackjackgame.GameStatus
import blackjack.domain.player.Player
import blackjack.domain.player.UserRole

class BlackjackJudgement(private val users: List<UserRole>) {

    private val dealer = users.first { it.isDealer() }

    fun updateGameJudgement(): List<UserRole> {
        var players = users.filter { !it.isDealer() }
        players = updateGame(players).toMutableList()
        players.add(dealer)
        return players.toList()
    }

    private fun updateGame(players: List<UserRole>): List<UserRole> {
        if (dealer.getScore() >= BURST_SCORE_MIN) {
            dealer.gameStatus.judgements.add(Loose())
            return players.map { Player(it.userSetting, GameStatus(it.gameStatus.state, mutableListOf(Win()))) }
                .toList()
        }
        return players.map { updateGameJudgement(it, dealer.getScore()) }
            .toList()
    }

    private fun updateGameJudgement(it: UserRole, dealerScore: Int): UserRole {
        return when {
            it.getScore() == dealerScore -> getUserRoleWithSameDealer(it)
            it.getScore() < dealerScore -> getUserRoleWithLessDealer(it)
            else -> getUserRoleWithGreaterDealer(it)
        }
    }

    private fun getUserRoleWithSameDealer(userRole: UserRole): UserRole {
        userRole.gameStatus.judgements.add(Push())
        dealer.gameStatus.judgements.add(Push())
        return userRole
    }

    private fun getUserRoleWithLessDealer(userRole: UserRole): UserRole {
        userRole.gameStatus.judgements.add(Loose())
        dealer.gameStatus.judgements.add(Win())
        return userRole
    }

    private fun getUserRoleWithGreaterDealer(userRole: UserRole): UserRole {
        userRole.gameStatus.judgements.add(Win())
        dealer.gameStatus.judgements.add(Loose())
        return userRole
    }

    companion object {
        private const val BURST_SCORE_MIN = 22
    }
}
