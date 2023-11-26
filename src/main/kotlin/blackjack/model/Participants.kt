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

    private fun isGameOver(): Boolean {
        return players.isGameOver() || dealer.isBurst()
    }

    fun isContinue(): Boolean {
        return !isGameOver()
    }

    fun count(): Int {
        return players.count() + 1
    }
}
