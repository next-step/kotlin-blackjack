package blackjack.core

import blackjack.core.card.Card
import blackjack.core.card.Denomination
import blackjack.core.card.Suit
import blackjack.core.player.Player
import blackjack.core.player.Players

class CardDispenser {
    private val cards: Iterator<Card> =
        Denomination.entries
            .flatMap { denomination ->
                Suit.entries.map { suit ->
                    Card(denomination, suit)
                }
            }
            .toList()
            .shuffled()
            .iterator()

    fun dealDefault(players: Players): Boolean {
        return players.all { dealDefault(it) }
    }

    fun dealDefault(player: Player): Boolean {
        return (0..<DEFAULT_CARD_NUM).all { deal(player) }
    }

    fun deal(
        players: Players,
        cardCount: Int,
    ): Boolean {
        return players.all { deal(it, cardCount) }
    }

    fun deal(
        player: Player,
        cardCount: Int,
    ): Boolean {
        return (0..<cardCount).all { deal(player) }
    }

    fun deal(player: Player): Boolean {
        if (cards.hasNext().not()) {
            return false
        }

        player.draw(cards.next())
        return true
    }

    fun checkRemainCard(): Boolean {
        return cards.hasNext()
    }

    companion object {
        private const val DEFAULT_CARD_NUM = 2
    }
}
