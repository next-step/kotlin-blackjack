package blackjack.domain.card

interface DrawStrategy {

    fun fetchCard(): Card
}
