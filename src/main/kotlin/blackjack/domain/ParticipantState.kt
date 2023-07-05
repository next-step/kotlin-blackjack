package blackjack.domain

enum class ParticipantState(
    val canDraw: Boolean,
) {
    HIT(true),
    STAND(false),
    BUST(false),
    BLACK_JACK(false),
    ;

    companion object {
        fun of(participant: Participant): ParticipantState {
            if (participant.state == STAND) {
                return STAND
            }

            val sum = participant.sumOfCards()

            if (sum == Cards.BLACK_JACK_SCORE) {
                return BLACK_JACK
            }

            if (sum < Cards.BLACK_JACK_SCORE) {
                return HIT
            }

            return BUST
        }
    }
}
