package blackjack.domain.player

data class UserInfo(private val info: Pair<Dealer, Users>) {
    val dealer
        get() = info.first
    val players
        get() = info.second
}
