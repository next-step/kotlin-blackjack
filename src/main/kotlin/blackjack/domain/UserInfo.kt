package blackjack.domain

import blackjack.domain.player.Dealer
import blackjack.domain.player.Players

class UserInfo(private val info: Pair<Dealer, Players>) {
    val dealer
        get() = info.first
    val players
        get() = info.second
}
