package blackjack.domain.card

fun cards(vararg card: Card): Cards {
    return Cards(card.toList())
}

fun bustCards(): Cards {
    return cards(
        CardFixture.heartJack,
        CardFixture.heartQueen,
        CardFixture.heartKing,
    )
}

fun notBustCards(): Cards {
    return cards(
        CardFixture.heartTwo,
        CardFixture.heartThree,
    )
}
