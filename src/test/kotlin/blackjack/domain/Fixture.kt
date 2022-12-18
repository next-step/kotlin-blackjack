package blackjack.domain

fun Dealer(vararg values: Card): Dealer {
    val dealer = Dealer()
    values.forEach {
        dealer.drawCard(it)
    }

    return dealer
}

fun Player(vararg values: Card): Player {
    val player = Player("플레이어")
    values.forEach {
        player.cards.add(it)
    }
    return player
}
