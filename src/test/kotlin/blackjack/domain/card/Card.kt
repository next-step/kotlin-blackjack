package blackjack.domain.card

fun heartAce(): Card {
    return heartCard(CardDenomination.ACE)
}

fun heartTwo(): Card {
    return heartCard(CardDenomination.TWO)
}

fun heartThree(): Card {
    return heartCard(CardDenomination.THREE)
}

fun heartFour(): Card {
    return heartCard(CardDenomination.FOUR)
}

fun heartFive(): Card {
    return heartCard(CardDenomination.FIVE)
}

fun heartSeven(): Card {
    return heartCard(CardDenomination.SEVEN)
}

fun heartTen(): Card {
    return heartCard(CardDenomination.TEN)
}

fun heartJack(): Card {
    return heartCard(CardDenomination.JACK)
}

fun heartQueen(): Card {
    return heartCard(CardDenomination.QUEEN)
}

fun heartKing(): Card {
    return heartCard(CardDenomination.KING)
}

fun heartCard(denomination: CardDenomination): Card {
    return Card(
        shape = CardShape.HEART,
        denomination = denomination,
    )
}

fun spadeAce(): Card {
    return spadeCard(CardDenomination.ACE)
}

fun spadeFour(): Card {
    return spadeCard(CardDenomination.FOUR)
}

fun spadeTen(): Card {
    return spadeCard(CardDenomination.TEN)
}

fun spadeQueen(): Card {
    return spadeCard(CardDenomination.QUEEN)
}

fun spadeKing(): Card {
    return spadeCard(CardDenomination.KING)
}

fun spadeCard(denomination: CardDenomination): Card {
    return Card(
        shape = CardShape.SPADE,
        denomination = denomination,
    )
}
