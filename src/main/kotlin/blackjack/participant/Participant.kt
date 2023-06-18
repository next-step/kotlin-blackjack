package blackjack.participant

import blackjack.domain.card.Card
import blackjack.domain.state.State
import blackjack.participant.player.PlayerName
import blackjack.participant.player.PlayerResult

typealias DrawingEvent = () -> Card

sealed class Participant<T>(private val playerName: PlayerName, protected var state: State) {

    abstract fun play(gameEvent: T, drawingEvent: DrawingEvent): PlayerResult

    fun getName() = playerName.name

    fun getCards() = state.playingCards
}
