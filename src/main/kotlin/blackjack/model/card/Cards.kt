package blackjack.model.card

import blackjack.model.player.BLACKJACK_MAX_NUMBER

const val BOTTOM_CARD_NUMBER = 0

class Cards(cards: List<Card> = listOf()) {
    private val _cards: MutableList<Card> = cards.deepCopy() as MutableList<Card>
    val cards: List<Card>
        get() = _cards.deepCopy()

    private fun List<Card>.deepCopy(): List<Card> = map { it.copy() }

    fun draw(): Card {
        _cards.removeAt(BOTTOM_CARD_NUMBER)
        return _cards[BOTTOM_CARD_NUMBER]
    }

    operator fun plus(card: Card): Cards {
        _cards.add(card)
        return Cards(_cards)
    }

    fun getCount(): Int {
        return _cards.size
    }

    fun sumByPoint(): Int {
        return _cards.sumBy { it.denomination.point }
    }

    private fun sumByOptionalPoint(): Int {
        return _cards.sumBy { it.denomination.pointOptional }
    }

    fun getDisplayCards(): String {
        return _cards.joinToString(separator = ",") { "${it.denomination.title}${it.suit.title}" }
    }

    fun getBlackjackPoint(): Int {
        val pointSum = sumByPoint()
        val pointSumOptional = sumByOptionalPoint()

        if (pointSumOptional < BLACKJACK_MAX_NUMBER) return pointSumOptional

        return pointSum
    }

    companion object {
        fun generate(): Cards {
            val cards = mutableListOf<Card>()

            for (suit in Card.Suit.values()) {
                for (denomination in Card.Denomination.values()) {
                    cards.add(Card(suit, denomination))
                }
            }
            return Cards(cards.shuffled() as MutableList<Card>)
        }
    }
}
