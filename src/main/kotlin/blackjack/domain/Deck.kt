package blackjack.domain

interface Deck {
    fun generate()

    fun drawCard(): Card
}
