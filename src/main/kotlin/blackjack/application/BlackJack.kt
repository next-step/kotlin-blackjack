package blackjack.application

import blackjack.domain.participant.BlackJack
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Match
import blackjack.domain.participant.Money
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player
import blackjack.domain.result.BlackJackResult
import blackjack.domain.result.ParticipantResult

class BlackJack(
    private val dealer: Dealer,
    private val players: List<Player>,
) {

    init {
        require(players.size in PLAYER_RANGE) {
            "블랙잭을 진행하기 위한 인원은 ${PLAYER_RANGE.first} ~ ${PLAYER_RANGE.last} 명입니다. 현재 인원 = ${players.size}"
        }
    }

    fun distribute() {
        players.forEach { participant ->
            participant.receive(dealer.draw())
            participant.receive(dealer.draw())
        }
        dealer.receive(dealer.draw())
        dealer.receive(dealer.draw())
    }

    fun confirmBlackJackPlayer() {
        if (dealer.state is BlackJack) return
        players.forEach {
            it.winIfBlackJackAfterDistribution(dealer)
        }
    }

    private fun Player.winIfBlackJackAfterDistribution(dealer: Dealer) {
        if (state !is BlackJack || hand.size() != DISTRIBUTED_CARDS_SIZE) {
            return
        }
        val money = applyBonus(bettingMoney)
        earn(money)
        dealer.lost(money)
    }

    private fun applyBonus(money: Money): Money {
        return money * BONUS_RATE
    }

    tailrec fun dealWith(participant: Participant, askHit: (String) -> Boolean, openHand: (Participant) -> Unit) {
        if (!participant.isPlayable { askHit(participant.name) }) {
            return
        }
        participant.receive(dealer.draw())
        openHand(participant)
        dealWith(participant, askHit, openHand)
    }

    fun matching(): BlackJackResult {
        players.forEach { player ->
            bet(player, dealer)
        }
        val participantResults = (listOf(dealer) + players).map {
            ParticipantResult(it.name, it.profit)
        }
        return BlackJackResult(participantResults)
    }

    private fun bet(player: Player, dealer: Dealer) {
        val bettingMoney = player.bettingMoney
        when (player.match(dealer)) {
            Match.WIN -> {
                player.earn(bettingMoney)
                dealer.lost(bettingMoney)
            }
            Match.LOSE -> {
                player.lost(bettingMoney)
                dealer.earn(bettingMoney)
            }
            Match.DRAW -> {}
        }
    }

    companion object {
        private val PLAYER_RANGE = 2..6
        private const val DISTRIBUTED_CARDS_SIZE = 2
        private const val BONUS_RATE = 1.5
    }
}
