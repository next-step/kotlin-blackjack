package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Hand

open class Player(
    val name: PlayerName,
    val hand: Hand,
) {
    var status: PlayerStatus = PlayerStatus.INIT
        private set

    fun isFinished(): Boolean = status in setOf(PlayerStatus.BUST, PlayerStatus.BUST, PlayerStatus.STAY)
    fun hit(card: Card) {
        hand.add(card)

        if (hand.bust()) {
            status = PlayerStatus.BUST
            return
        }

        if (hand.blackjack()) {
            status = PlayerStatus.BLACKJACK
            return
        }

        status = PlayerStatus.HIT
    }

    fun stay() {
        status = PlayerStatus.STAY
    }

    fun total(): Int {
        return hand.total()
    }

    companion object {
        fun of(name: PlayerName, openCards: OpenCards): Player {
            return Player(name, Hand.create(openCards))
        }
    }
}




