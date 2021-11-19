package domain.player

import domain.card.CardGenerator
import domain.card.PlayingCards
import domain.player.state.PlayerState
import domain.player.state.Started
import exception.IllegalPlayException

open class Player private constructor(private val playerInfo: PlayerInfo, private var playerState: PlayerState) {
    constructor(playerInfo: PlayerInfo, cards: PlayingCards) : this(playerInfo, Started(cards).nextState())
    constructor(playerInfo: PlayerInfo, cardGenerator: CardGenerator) : this(playerInfo, PlayingCards(cardGenerator))

    fun name(): String = playerInfo.name()
    fun cards(): PlayingCards = playerState.cards
    fun isFinished(): Boolean = playerState.isFinished()
    fun score(): Int = playerState.score()
    fun win(other: Player): Boolean = score() > other.score()

    fun play(draw: Boolean, cardGenerator: CardGenerator) {
        if (isFinished()) {
            throw IllegalPlayException()
        }
        playerState = if (draw) playerState.draw(cardGenerator.getCard()) else playerState.stay()
    }
}
