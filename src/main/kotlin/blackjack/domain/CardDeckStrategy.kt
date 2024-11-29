package blackjack.domain

interface CardDeckStrategy {
    fun generate()

    fun drawCard(): Card
}
