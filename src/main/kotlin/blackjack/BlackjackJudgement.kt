package blackjack

import blackjack.judge.Loose
import blackjack.judge.Push
import blackjack.judge.Win

class BlackjackJudgement(private val users: List<UserRole>) {

    private val dealer = users.first { it.isDealer() }

    fun updateGameJudgement(): List<UserRole> {
        var players = users.filter { !it.isDealer() }
        val dealerScore = dealer.getScore()
        players = when (dealer.getScore() >= BURST_SCORE_MIN) {
            true -> {
                dealer.gameStatus.judgements.add(Loose())
                players
                    .map { Player(it.name, GameStatus(it.gameStatus.state, mutableListOf(Win()))) }
                    .toList()
            }
            else -> {
                players.map { updateGameJudgement(it, dealerScore) }
                    .toList()
            }
        }.toMutableList()
        players.add(dealer)
        return players.toList()
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
