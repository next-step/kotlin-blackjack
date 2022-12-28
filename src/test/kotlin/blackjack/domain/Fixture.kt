package blackjack.domain

fun Dealer(vararg values: Card): Dealer {
    val dealer = Dealer()
    values.forEach {
        dealer.hit(it)
    }

    return dealer
}

fun User(vararg values: Card): User {
    val user = User(name = Name("플레이어"), betAmount = BetAmount(0))
    values.forEach {
        user.cards.add(it)
    }
    return user
}

fun userOf(name: String): User {
    return User(name = Name(name), betAmount = BetAmount(0))
}

fun Users(vararg names: String): Users {
    val users = names.map { User(name = Name(it), betAmount = BetAmount(0)) }
    return Users(users)
}
