package blackjack.domain

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
    fun open(): OpenCards {
        return OpenCards(cardDeck.fetch(), cardDeck.fetch())
    }

    fun dealing(player: Player) {
        if (player.status == PlayerStatus.BUST || player.status == PlayerStatus.BLACKJACK) {
            throw RuntimeException()
        }
        player.hit(cardDeck.fetch())
    }

    fun countOfRemainCards(): Int {
        return cardDeck.countOfCards()
    }

    companion object {
        fun of(name: PlayerName, cardDeck: CardDeck): Dealer {
            return Dealer(cardDeck, name, Hand.empty())
        }
    }
}

class OpenCards(val first: Card, val second: Card)
