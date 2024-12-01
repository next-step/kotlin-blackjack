package blackjack.card

import blackjack.machine.BlackJackMachine

data class Cards(
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

    fun add(newCard: Card): Cards = Cards(cards = cards + newCard)

    fun draw(drawCard: Card): Cards? =
        Cards(cards = cards - drawCard)
            .takeIf { cards.contains(drawCard) }
}
