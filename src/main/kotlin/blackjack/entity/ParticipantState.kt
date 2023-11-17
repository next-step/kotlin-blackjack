package blackjack.entity

sealed class ParticipantState {
    object HIT : ParticipantState()
    object STAND : ParticipantState()
    object BUST : ParticipantState()
    object BLACKJACK : ParticipantState()
}
