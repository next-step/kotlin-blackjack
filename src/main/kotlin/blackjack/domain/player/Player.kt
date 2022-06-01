package blackjack.domain.player

class Player private constructor(
    private val name: Name,
    private val cardsInHand: CardsInHand
) {
    companion object {
        fun sit(name: Name): Player = Player(name, CardsInHand(emptyList()))
    }
}
