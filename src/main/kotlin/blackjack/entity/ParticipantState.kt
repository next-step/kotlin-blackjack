package blackjack.entity

sealed class ParticipantState {
    object INITIAL : ParticipantState()
    object HIT : ParticipantState()
    object STAND : ParticipantState()
    object BUST : ParticipantState()
    object BLACKJACK : ParticipantState()
}
