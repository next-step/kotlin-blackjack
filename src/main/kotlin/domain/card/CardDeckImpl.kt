package domain.card

import java.util.Stack

class CardDeckImpl : CardDeck {
    private val cards = Stack<Card>()

    init {
        createNewDeck().shuffled()
            .forEach {
                cards.push(it)
            }
    }

    override fun pop(): Card {
        return cards.pop()
    }

    private fun createNewDeck(): List<Card> {
        return createSpades()
            .plus(createHearts())
            .plus(createDiamonds())
            .plus(createClovers())
    }

    private fun createSpades(): List<Card> {
        return Denomination.values().map {
            Card.spade(it)
        }
    }

    private fun createHearts(): List<Card> {
        return Denomination.values().map {
            Card.heart(it)
        }
    }

    private fun createDiamonds(): List<Card> {
        return Denomination.values().map {
            Card.diamond(it)
        }
    }

    private fun createClovers(): List<Card> {
        return Denomination.values().map {
            Card.clover(it)
        }
    }
}
