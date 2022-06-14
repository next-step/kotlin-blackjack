package blackjack.domain.game

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.ParticipantStatus
import blackjack.domain.participant.Player

data class Result(
    private val dealer: Dealer,
    private val players: List<Player>,
    private val _scoreByParticipantName: MutableMap<String, Score> = mutableMapOf(),
    private val _totalEarningsByParticipantName: MutableMap<String, Int> = mutableMapOf()
) {
    val scoreByParticipantName: Map<String, Score>
        get() = _scoreByParticipantName.map { it.key to it.value.copy() }.toMap()
    val totalEarningsByParticipantName: Map<String, Int>
        get() = mapOf(dealer.name to dealer.money.bat) + players.associate { it.name to it.money.bat }

    init {
        _scoreByParticipantName[dealer.name] = Score()
        players.forEach { player -> _scoreByParticipantName[player.name] = Score() }
    }

    fun decideWinner(dealer: Dealer, player: Player) {
        if (dealer.status != ParticipantStatus.BLACKJACK && player.status == ParticipantStatus.BLACKJACK) {
            val earning = calculateEarningByProfit(player, BLACKJACK_PROFIT_PERCENTAGE)
            win(player, winMoney = earning)
            lose(dealer, loseMoney = earning)
            return
        }
        if (dealer.status == ParticipantStatus.BUST) {
            win(player)
            lose(dealer)
            return
        }
        if (player.status == ParticipantStatus.BUST) {
            win(dealer, player.money.bat)
            lose(player, player.money.bat)
            return
        }

        if (dealer.score() > player.score()) {
            win(dealer, player.money.bat)
            lose(player, player.money.bat)
        }
        if (dealer.score() < player.score()) {
            win(player)
            lose(dealer)
        }
    }

    private fun calculateEarningByProfit(participant: Participant, profitPercentage: Double) =
        (participant.money.bat * profitPercentage).toInt()

    private fun win(participant: Participant, winMoney: Int = 0) {
        updateParticipantResultCount(participant, isWin = true)
        participant.money.accBatMoney(winMoney)
    }

    private fun lose(participant: Participant, loseMoney: Int = 0) {
        updateParticipantResultCount(participant, isWin = false)
        participant.money.accBatMoney(-loseMoney)
    }

    private fun updateParticipantResultCount(participant: Participant, isWin: Boolean) {
        val score = checkNotNull(_scoreByParticipantName[participant.name]) { "participant must be added to map" }
        if (isWin) {
            score.win += 1
        } else {
            score.lose += 1
        }
    }

    companion object {
        const val BLACKJACK_PROFIT_PERCENTAGE = 0.5
    }
}
