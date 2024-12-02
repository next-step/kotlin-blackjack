package blackjack.player

import blackjack.card.Card
import blackjack.card.Rank
import blackjack.machine.BlackJackMachine

data class Hand(
    val cards: List<Card>,
) {
    fun sum(): Int =
        cards
            .sumOf { it.rank.value }
            .takeIf { it <= BlackJackMachine.BLACKJACK }
            ?: sumWithHandlingAce()

    private fun sumWithHandlingAce(): Int =
        cards.fold(initial = 0) { sum, card ->
            when {
                (card.rank == Rank.ACE && sum + card.rank.value > BlackJackMachine.BLACKJACK) -> sum + card.rank.alternative
                else -> sum + card.rank.value
            }
        }

    fun add(newCard: Card): Hand = Hand(cards = cards + newCard)
}
