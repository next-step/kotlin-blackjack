package blackjack.domain

import blackjack.domain.Rank.Companion.ACE_MAX
import blackjack.domain.Rank.Companion.ACE_MIN

class Player(
    val name: String,
    cards: Cards = Cards()
) {
    var cards: Cards = cards
        private set

    init {
        require(name.isNotEmpty() && name.isNotBlank())
    }

    fun receiveCards(newCards: List<Card>): Player {
        cards = Cards(cards.values + newCards)
        return this
    }

    fun receiveCard(newCard: Card): Player {
        cards = Cards(cards.values + newCard)
        return this
    }

    fun calculateScore(): Int {
        var score = cards.sumOfScoreWithAceAsOne

        repeat(getNumberOfAce()) {
            if (isValidBlackjackScoreWithAceAsEleven(score)) {
                score += ACE_MAX - ACE_MIN
            }
        }

        return score
    }

    private fun isValidBlackjackScoreWithAceAsEleven(score: Int) = score + ACE_MAX - ACE_MIN <= BLACK_JACK

    private fun getNumberOfAce() = cards.values.count { it.rank == Rank.ACE }

    companion object {
        const val DEFAULT_CARD_SIZE = 2
    }
}
