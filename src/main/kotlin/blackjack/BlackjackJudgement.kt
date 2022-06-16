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
                dealer.judgements.add(Loose())
                players
                    .map { Player(it.name, it.state, mutableListOf(Win())) }
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
            it.getScore() == dealerScore -> {
                it.judgements.add(Push())
                dealer.judgements.add(Push())
                it
            }

            it.getScore() < dealerScore -> {
                it.judgements.add(Loose())
                dealer.judgements.add(Win())
                it
            }

            else -> {
                it.judgements.add(Win())
                dealer.judgements.add(Loose())
                it
            }
        }
    }

    companion object {
        private const val BURST_SCORE_MIN = 22
    }
}
