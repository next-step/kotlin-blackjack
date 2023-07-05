package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.Hand

class Dealer private constructor(
    val cardDeck: CardDeck,
    name: PlayerName,
    hand: Hand,
): Player(name, hand) {
    fun openSelf() {
        val openCards = open()
        hand.add(openCards.first)
        hand.add(openCards.second)
    }
    fun open(): OpenCards = OpenCards(cardDeck.fetch(), cardDeck.fetch())

    fun dealing(player: Player) {
        if (player.isFinished()) {
            throw RuntimeException()
        }
        player.hit(cardDeck.fetch())
    }

    fun hitSelf() = hit(cardDeck.fetch())

    fun shouldHit(): Boolean = total() <= SHOULD_HIT_SCORE

    fun firstOpenCard(): Card = hand.first()

    companion object {
        const val SHOULD_HIT_SCORE = 16
        fun of(name: PlayerName, cardDeck: CardDeck): Dealer {
            return Dealer(cardDeck, name, Hand.empty())
        }
    }
}

class OpenCards(val first: Card, val second: Card)
