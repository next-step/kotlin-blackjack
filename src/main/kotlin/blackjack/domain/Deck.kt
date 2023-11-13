package blackjack.domain

interface Deck {

    fun init(): List<Card>

    fun hit(): Card
}
