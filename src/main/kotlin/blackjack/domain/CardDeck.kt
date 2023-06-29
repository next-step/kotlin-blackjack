package blackjack.domain

import blackjack.domain.Card.Companion.newCard
import blackjack.domain.CardDeck.Companion.ALL_CARDS

interface CardDeck {

    fun pickCard(): Card

    fun remainCardsCount(): Int

    fun canPick(): Boolean = remainCardsCount() > NO_CARDS

    companion object {
        private const val NO_CARDS = 0

        val ALL_CARDS: Cards by lazy {
            val cardimages = CardImage.values().toList()
            val cardLevels = CardLevel.values().toList()

            cardimages.map { cardImage ->
                cardLevels.map { cardLevel ->
                    newCard {
                        cardImage with cardLevel
                    }
                }
            }.flatten().let { Cards(it) }
        }
    }
}

class ShuffledCardDeck : CardDeck {
    private val cards: MutableList<Card> = ALL_CARDS.all().shuffled().toMutableList().let { ArrayDeque(it) }

    override fun pickCard(): Card = cards.removeFirstOrNull() ?: throw BadCardPickException()

    override fun remainCardsCount(): Int = cards.size
}
