package blackjack.domain.game

import blackjack.domain.participant.Participant

enum class Rank(val value: Double) {
    BLACKJACK(1.5),
    WON(1.0),
    LOST(-1.0),
    DRAW(1.0);

    companion object {
        fun of(participant: Participant, opponent: Participant): Rank {
            return when {
                participant.hasBlackJack() && opponent.hasBlackJack() -> DRAW
                participant.hasBlackJack() -> BLACKJACK
                participant.score() == opponent.score() -> DRAW
                opponent.score() > BlackJack.BLACKJACK_MAX_SCORE -> WON
                participant.score() > BlackJack.BLACKJACK_MAX_SCORE -> LOST
                participant.score() > opponent.score() -> WON
                participant.score() < opponent.score() -> LOST
                else -> DRAW
            }
        }
    }
}
