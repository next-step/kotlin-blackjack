package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.state.FinishState
import blackjack.domain.state.RunningState
import blackjack.domain.state.State
import blackjack.event.GameEvent

private typealias DrawingEvent = () -> Card

class Player(private val playerName: PlayerName, private var state: State) {

    fun play(gameEvent: GameEvent, drawingEvent: DrawingEvent): PlayerResult = when (val playState = state) {
        is RunningState -> hit(
            gameEvent = gameEvent,
            runningState = playState,
            drawingEvent = drawingEvent,
        )

        is FinishState -> PlayerResult(
            player = this,
            score = playState.resultScore(),
        )
    }

    private fun hit(
        gameEvent: GameEvent,
        runningState: RunningState,
        drawingEvent: DrawingEvent,
    ): PlayerResult = if (gameEvent.hitOrNot(playerName.name)) {
        state = runningState.draw(card = drawingEvent())
        gameEvent.resultEvent(this)
        play(gameEvent = gameEvent) { drawingEvent() }
    } else {
        val finishState = runningState.stay()
        state = finishState
        PlayerResult(player = this, score = finishState.resultScore())
    }

    private fun convertExposeCards(): List<Pair<String, String>> = this.getCards()
        .map { it.denomination.exposeName to it.suit.exposeName }

    fun getName() = playerName.name

    fun getCards() = state.playingCards
}
