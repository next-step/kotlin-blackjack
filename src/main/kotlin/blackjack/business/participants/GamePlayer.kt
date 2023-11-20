package blackjack.business.participants

import blackjack.business.util.BlackJackConst

class GamePlayer(name: String, playerCards: PlayerCards = PlayerCards()) : BasePlayer(name, playerCards) {

    override fun canDrawCard(): Boolean {
        return playerCards.sum() < BlackJackConst.BLACKJACK
    }
}
