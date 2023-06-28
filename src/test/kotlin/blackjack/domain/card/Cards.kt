package blackjack.domain.card

fun cards(vararg card: Card): Cards {
    return Cards(card.toList())
}

fun bustCards(): Cards {
    return cards(
        heartJack(),
        heartQueen(),
        heartKing(),
    )
}

fun notBustCards(): Cards {
    return cards(
        heartTwo(),
        heartThree(),
    )
}
