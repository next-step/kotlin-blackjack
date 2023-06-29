package blackjack.model

fun interface BettingMoneyProvider {
    fun bettingMoney(name: PlayerName): Int
}
