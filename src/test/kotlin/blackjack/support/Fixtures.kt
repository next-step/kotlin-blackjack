package blackjack.support

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.DealerHand
import blackjack.domain.Deck
import blackjack.domain.Hand
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.Rank
import blackjack.domain.StubDeck

object Fixtures {
    fun playersOf(vararg players: Player): Players = Players(players.toList())

    fun createPlayers(
        names: List<String>,
        deck: Deck,
    ): Players =
        Players.from(names).apply {
            dealRoundOfCardsFrom(deck)
            dealRoundOfCardsFrom(deck)
        }

    fun createPlayer(
        deck: Deck,
        name: String = "jack",
    ): Player =
        Player(name).apply {
            initialDrawFrom(deck)
            initialDrawFrom(deck)
        }

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

    fun createDealer(deck: Deck = StubDeck.from(Rank.TWO, Rank.THREE)) =
        Dealer().apply {
            drawFrom(deck)
            drawFrom(deck)
        }

    fun createBlackjackDealer(): Dealer {
        val deck = StubDeck.from(Rank.ACE, Rank.KING)
        return Dealer().apply {
            drawFrom(deck)
            drawFrom(deck)
        }
    }

    fun createBustedDealer(): Dealer {
        val deck = StubDeck.from(Rank.KING, Rank.QUEEN, Rank.JACK)
        return Dealer().apply {
            drawFrom(deck)
            drawFrom(deck)
            drawFrom(deck)
        }
    }

    fun createHand(vararg ranks: Rank): Hand =
        Hand(
            ranks.map { Card(StubDeck.DUMMY_SUIT, it) },
        )

    fun createDealerHand(vararg ranks: Rank): DealerHand {
        val deck = StubDeck.from(*ranks)
        return DealerHand().apply {
            repeat(ranks.size) {
                drawFrom(deck)
            }
        }
    }
}
