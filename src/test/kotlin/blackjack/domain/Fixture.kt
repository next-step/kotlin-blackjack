package blackjack.domain

fun Dealer(vararg values: Card): Dealer {
    val dealer = Dealer()
    values.forEach {
        dealer.hit(it)
    }

    return dealer
}

fun User(vararg values: Card): User {
    val user = User(Name("플레이어"))
    values.forEach {
        user.cards.add(it)
    }
    return user
}

fun Users(vararg names: String): Users {
    val users = names.map { User(Name(it)) }
    return Users(users)
}
