package blackjack.domain

interface Deck {
    val size: Int

    fun draw(): Card
}
