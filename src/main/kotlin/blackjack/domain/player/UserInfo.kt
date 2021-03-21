package blackjack.domain.player

import blackjack.domain.player.Dealer
import blackjack.domain.player.Users

data class UserInfo(private val info: Pair<Dealer, Users>) {
    val dealer
        get() = info.first
    val players
        get() = info.second
}
