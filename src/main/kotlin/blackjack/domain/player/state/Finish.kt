package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.card.Score
import blackjack.domain.player.state.hands.Hands
import blackjack.error.InvalidDrawException
import blackjack.error.InvalidMapToPlayStateException

sealed class Finish(hands: Hands) : PlayerState(hands) {

    override fun isFinished(): Boolean = true

    override fun score(): Score = hands.score()

    override fun draw(card: Card): PlayerState = throw InvalidDrawException(this::class.toString())

    override fun stay(): PlayerState = throw InvalidMapToPlayStateException(this::class.toString())
}
