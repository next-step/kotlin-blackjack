package blackjack.domain

fun Dealer(vararg values: Card): Dealer = Dealer(cards = values.toCards())

fun User(vararg values: Card, betAmount: BetAmount = BetAmount(0)): User {
    return User(
        name = Name("플레이어"),
        cards = values.toCards(),
        betAmount = betAmount,
    )
}

fun userOf(name: String): User {
    return User(name = Name(name), betAmount = BetAmount(0))
}

fun Users(vararg names: String): Users {
    val users = names.map { User(name = Name(it), betAmount = BetAmount(0)) }
    return Users(users)
}

private fun Array<out Card>.toCards(): Cards = Cards(this.toList())
