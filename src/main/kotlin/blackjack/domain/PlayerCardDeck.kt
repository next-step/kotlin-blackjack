package blackjack.domain

import blackjack.BlackJackGame.Companion.BLACK_JACK_NUMBER

class PlayerCardDeck(
    cards: List<Card>,
) {
    constructor(vararg card: Card) : this(card.toList())

    var score: CardScore = CardScore()
        private set

    var cards: List<Card> = cards
        private set

    fun addCard(card: Card) {
        cards += card
    }

    fun calculateScore() {
        val aceCards = cards.filter { it.number == CardNumber.ACE }
        var cardsSum = cards.filter { it.number != CardNumber.ACE }.sumOf { it.number.value }
        cardsSum += aceCards.sumOf { ACE_ELEVEN }
        aceCards.forEach { _ ->
            if (cardsSum > BLACK_JACK_NUMBER) {
                cardsSum -= ACE_ELEVEN - ACE_ONE
            } else {
                score = CardScore(cardsSum)
                return
            }
        }
        score = CardScore(cardsSum)
    }

    companion object {
        private const val ACE_ONE = 1
        private const val ACE_ELEVEN = 11
    }
}
