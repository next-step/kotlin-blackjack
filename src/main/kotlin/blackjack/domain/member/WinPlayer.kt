package blackjack.domain.member

import blackjack.domain.Cards

class WinPlayer(name: String, cards: Cards) : Player(name, cards) {

    companion object {
        fun init(player: Player): WinPlayer = WinPlayer(player.name, player.cards)
    }
}
