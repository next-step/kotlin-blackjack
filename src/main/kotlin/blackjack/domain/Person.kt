package blackjack.domain

abstract class Person(val name: String) {

    private val _myCards = mutableListOf<Card>()
    val myCards: List<Card>
        get() = _myCards
}
