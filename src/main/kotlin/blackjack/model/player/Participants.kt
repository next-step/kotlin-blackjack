package blackjack.model.player

import blackjack.model.card.pack.Pack
import blackjack.model.player.playable.impl.Dealer
import blackjack.model.result.DealerResult

class Participants(
    val players: Players,
    val dealer: Dealer,
) {

    fun dealing(pack: Pack) {
        players.dealing(pack)
        dealer.dealing(pack)
    }

    fun isContinue(): Boolean {
        return players.hasAnyAlivePlayer() && (!dealer.isBurst())
    }

    fun count(): Int {
        return players.count() + 1
    }

    fun dealerResult(): DealerResult {
        return this.dealer.dealerResult(this.players)
    }
}
