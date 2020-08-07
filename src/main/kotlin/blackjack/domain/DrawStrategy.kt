package blackjack.domain

interface DrawStrategy {

    fun fetchCard(): Card
}
