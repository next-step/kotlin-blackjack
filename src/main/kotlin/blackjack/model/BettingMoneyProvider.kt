package blackjack.model

fun interface BettingMoneyProvider {
    fun bet(name: PlayerName): Money
}
