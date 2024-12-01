package blackjack

enum class Suit(val koreanName: String) {
    SPADES("스페이드"),
    CLUBS("클로버"),
    DIAMONDS("다이아몬드"),
    HEARTS("하트"), ;

    fun generateAllCards(): List<Card> {
        return CardNumberFactory.all().map { Card(it, this) }
    }
}
