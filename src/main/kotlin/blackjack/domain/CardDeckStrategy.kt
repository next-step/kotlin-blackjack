package blackjack.domain

interface CardDeckStrategy {
    fun drawCard(): Card
}