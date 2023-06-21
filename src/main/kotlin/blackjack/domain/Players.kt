package blackjack.domain

@JvmInline
value class Players(private val values: List<Player>) : List<Player> by values
