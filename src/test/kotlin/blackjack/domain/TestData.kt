package blackjack.domain

val SPADE_ACE = Card(Symbol.SPADE, CardNumber.ACE)
val SPADE_TWO = Card(Symbol.SPADE, CardNumber.TWO)
val SPADE_SIX = Card(Symbol.SPADE, CardNumber.SIX)
val SPADE_JACK = Card(Symbol.SPADE, CardNumber.JACK)

val HEART_ACE = Card(Symbol.HEART, CardNumber.ACE)
val HEART_TWO = Card(Symbol.HEART, CardNumber.TWO)
val HEART_JACK = Card(Symbol.HEART, CardNumber.JACK)

val DIAMOND_ACE = Card(Symbol.DIAMOND, CardNumber.ACE)
val DIAMOND_TWO = Card(Symbol.DIAMOND, CardNumber.TWO)
val DIAMOND_JACK = Card(Symbol.DIAMOND, CardNumber.JACK)

val SUM21_CARD2 = listOf(
    SPADE_ACE,
    HEART_JACK
)

val SUM21_CARDS = listOf(
    SPADE_ACE,
    HEART_JACK,
    SPADE_JACK
)

val UNDER_21_CARDS = listOf(
    SPADE_TWO,
    HEART_TWO,
    DIAMOND_JACK
)

val OVER_21_CARDS = listOf(
    SPADE_JACK,
    HEART_JACK,
    DIAMOND_JACK
)

fun BLACKJACK_PLAYER(): Player {
    val player = Player("player")

    SUM21_CARD2.map { player.receiveCard(it) }
    return player
}

fun BUST_PLAYER(): Player {
    val player = Player("player")

    OVER_21_CARDS.map { player.receiveCard(it) }
    return player
}

fun HIT_PLAYER_SUM6(): Player {
    val player = Player("player")

    listOf(
        SPADE_TWO,
        HEART_TWO,
        HEART_TWO
    ).map { player.receiveCard(it) }
    return player
}

fun HIT_PLYAER_SUM14(): Player {
    val player = Player("player")

    UNDER_21_CARDS.map { player.receiveCard(it) }
    return player
}

fun HIT_PLAYER_SUM20(): Player {
    val player = Player("player")

    listOf(
        HEART_JACK,
        SPADE_JACK
    ).map { player.receiveCard(it) }
    return player
}
