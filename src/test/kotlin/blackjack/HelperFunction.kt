package blackjack

fun createAceCard(): Card {
    return Card(CardNumber.ACE, CardMark.HEART)
}

fun createBasicCard(
    number: CardNumber,
    mark: CardMark,
): Card {
    return Card(number, mark)
}

fun createPlayers(number: Int): List<Player> {
    return (1..number).map { Player("name$it") }
}
