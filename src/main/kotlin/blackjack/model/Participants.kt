package blackjack.model

import blackjack.model.pack.Pack
import blackjack.model.playable.impl.Dealer

class Participants(
    val players: Players,
    val dealer: Dealer,
) {

    fun dealing(pack: Pack) {
        players.dealing(pack)
        dealer.dealing(pack)
    }

    fun isContinue(): Boolean {
        return players.hasAnyAlivePlayer() && dealer.isNotBurst()
    }

    fun count(): Int {
        return players.count() + 1
    }
}
