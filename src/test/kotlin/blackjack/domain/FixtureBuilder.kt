package blackjack.domain

import blackjack.domain.game.strategy.TakeMoreDealerStrategy
import blackjack.domain.game.strategy.TakeMorePlayerStrategy
import blackjack.domain.player.Player

class FixtureBuilder {

    companion object {

        class TakeMorePlayerFixture(private val boolean: Boolean) : TakeMorePlayerStrategy {
            override fun wantToTake(player: Player): Boolean {
                return boolean
            }
        }

        class TakeMoreDealerFixture(private val score: Int) : TakeMoreDealerStrategy {
            override fun canBeTakeOneCard(score: Int): Boolean {
                return this.score <= DEALER_TAKE_ONE_CARD_POLICY
            }

            companion object {
                const val DEALER_TAKE_ONE_CARD_POLICY = 16
            }
        }
    }
}
