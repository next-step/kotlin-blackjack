package blackjack.domain

class Dealer private constructor(val cards: ShuffledCards) {
    fun open(): OpenCards {
        return OpenCards(cards.fetch(), cards.fetch())
    }

    fun dealing(player: Player) {
        if (player.status == PlayerStatus.BUST || player.status == PlayerStatus.BLACKJACK) {
            throw RuntimeException()
        }
        player.hit(cards.fetch())
    }

    fun countOfRemainCards(): Int {
        return cards.countOfCards()
    }

    companion object {
        fun create(): Dealer {
            return Dealer(ShuffledCards())
        }
    }
}

class ShuffledCards {
    private val cards: MutableList<Card>
    init {
        val cardSuits = CardSuit.values().toList()
        val cardNumbers = CardNumber.values().toList()
        cards = cardSuits
            .map { cardShape -> cardNumbers.map { cardNumber -> Card(cardShape, cardNumber) } }
            .flatten()
            .toSet()
            .shuffled()
            .toMutableList()
    }

    fun fetch(): Card {
        if (cards.isEmpty()) throw RuntimeException()
        return cards.removeFirst()
    }

    fun countOfCards(): Int {
        return cards.size
    }
}
