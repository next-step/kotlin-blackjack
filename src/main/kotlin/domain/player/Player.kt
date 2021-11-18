package domain.player

import domain.card.CardGenerator
import domain.card.PlayingCards
import domain.player.state.PlayerState
import domain.player.state.Started
import exception.IllegalPlayException

class Player private constructor(private val playerInfo: PlayerInfo, private var playerState: PlayerState) {
    constructor(playerInfo: PlayerInfo, cards: PlayingCards) : this(playerInfo, Started(cards).nextState())

    fun name() = playerInfo.name()
    fun cards() = playerState.cards
    fun isFinished() = playerState.isFinished()

    fun play(draw: Boolean, cardGenerator: CardGenerator) {
        if (isFinished()) {
            throw IllegalPlayException()
        }
        playerState = if (draw) playerState.draw(cardGenerator.getCard()) else playerState.stay()
    }
}
