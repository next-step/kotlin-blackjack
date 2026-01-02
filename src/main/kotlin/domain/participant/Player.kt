package domain.participant

import domain.bet.BetMoney

class Player(val name: String, val betMoney: BetMoney) : Participant() {
    fun isBlackjack(): Boolean = hand.calculateScore(2) == ParticipantHand.BLACKJACK_MAX_SCORE
}
