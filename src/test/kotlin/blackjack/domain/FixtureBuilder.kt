package blackjack.domain

import blackjack.domain.game.TakeMoreStrategy
import blackjack.domain.player.Player

class FixtureBuilder {

    companion object {

        class TakeMoreFixture(private val boolean: Boolean) : TakeMoreStrategy {
            override fun wantToTake(player: Player): Boolean {
                return boolean
            }
        }
    }
}
