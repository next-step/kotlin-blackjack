package blackjack

import blackjack.card.BlackJackCard

class Player(
    val name: String,
) {
    private val cars: MutableList<BlackJackCard> = mutableListOf()
}
