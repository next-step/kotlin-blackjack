package blackjack.domain

sealed interface BlackjackGameEvent {
    data class Hit(val player: Player) : BlackjackGameEvent

    object Stay : BlackjackGameEvent

    object Dealer : BlackjackGameEvent
}
