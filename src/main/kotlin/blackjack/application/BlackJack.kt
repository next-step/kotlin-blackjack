package blackjack.application

import blackjack.domain.participant.Dealer
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
            player.match(dealer)
        }

        val participantResults = (listOf(dealer) + players).map {
            ParticipantResult(it.name, it.profit)
        }
        return BlackJackResult(participantResults)
    }

    fun confirmBlackJackPlayer() {
        players.forEach {
            it.winIfBlackJackAfterDistribution(dealer)
        }
    }

    companion object {
        private val PLAYER_RANGE = 2..6
    }
}
