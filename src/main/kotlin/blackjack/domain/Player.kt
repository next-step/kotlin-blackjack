package blackjack.domain

class Player(
    override val name: String,
    override val cards: Cards = Cards()
) : BlackJackPlayer {
    override val cardSet get() = cards.cards

    override fun isHit(): Boolean {
        return !cards.score().burst()
    }
}
