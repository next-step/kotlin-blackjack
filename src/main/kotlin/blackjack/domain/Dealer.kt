package blackjack.domain

class Dealer(
    private val gameCardsSet: GameCardsSet
) : Player("딜러", gameCardsSet)
