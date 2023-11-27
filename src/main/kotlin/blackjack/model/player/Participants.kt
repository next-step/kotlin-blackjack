package blackjack.model.player

import blackjack.model.card.pack.Pack
import blackjack.model.player.Players
import blackjack.model.player.playable.impl.Dealer

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
