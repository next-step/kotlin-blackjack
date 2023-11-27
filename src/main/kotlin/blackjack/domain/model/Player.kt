package blackjack.domain.model

class Player(
    val name: PlayerName,
    private var cards: Cards
) {

    fun isPossibleToHit(): Boolean {
        return cards.isPossibleToHit()
    }

    fun append(card: Card): Player {
        cards += card

        return this
    }

    fun cards(): Cards {
        return cards
    }
}
