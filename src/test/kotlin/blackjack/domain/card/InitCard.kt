package blackjack.domain.card

fun initCard(card1: Card, card2: Card): InitCard {
    return InitCard(Cards(listOf(card1, card2)))
}
