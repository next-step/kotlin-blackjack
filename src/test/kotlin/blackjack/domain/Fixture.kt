package blackjack.domain

fun Dealer(vararg values: Card): Dealer {
    val dealer = Dealer()
    values.forEach {
        dealer.hit(it)
    }

    return dealer
}

fun Player(vararg values: Card): Player {
    val user = User("플레이어")
    values.forEach {
        user.cards.add(it)
    }
    return user
}

fun Players(vararg names: String): Players {
    val users = names.map { User(it) }
    return Players(users)
}
