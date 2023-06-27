package blackjack.domain.card

fun cards(vararg card: Card): Cards {
    return Cards(card.toList())
}

fun bustCards(): Cards {
    return cards(
        heartCard(CardDenomination.JACK),
        heartCard(CardDenomination.QUEEN),
        heartCard(CardDenomination.KING),
    )
}

fun notBustCards(): Cards {
    return cards(
        heartCard(CardDenomination.TWO),
        heartCard(CardDenomination.THREE),
    )
}
