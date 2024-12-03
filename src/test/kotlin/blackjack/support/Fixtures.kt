package blackjack.support

import blackjack.domain.Player
import blackjack.domain.Rank
import blackjack.domain.StubDeck

object Fixtures {
    fun createBlackjackPlayer(name: String = "jack"): Player {
        val deck = StubDeck.from(Rank.ACE, Rank.KING)
        return Player(name).apply {
            initialDrawFrom(deck)
            initialDrawFrom(deck)
        }
    }

    fun createBustedPlayer(name: String = "jack"): Player {
        val deck = StubDeck.from(Rank.KING, Rank.QUEEN, Rank.JACK)
        return Player(name).apply {
            initialDrawFrom(deck)
            initialDrawFrom(deck)
            hit(deck)
        }
    }

    fun createStandingPlayer(name: String = "jack"): Player =
        Player(name).apply {
            stand()
        }
}
