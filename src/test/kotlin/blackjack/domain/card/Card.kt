package blackjack.domain.card

fun heartCard(denomination: CardDenomination): Card {
    return Card(
        shape = CardShape.HEART,
        denomination = denomination,
    )
}

fun spadeCard(denomination: CardDenomination): Card {
    return Card(
        shape = CardShape.SPADE,
        denomination = denomination,
    )
}
