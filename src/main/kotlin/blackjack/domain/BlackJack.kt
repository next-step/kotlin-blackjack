package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class BlackJack(
    private val deck: Deck = Deck.default(),
    private val players: Players
) {
    val hittablePlayers get() = players.hittablePlayers()
    val isEnd: Boolean get() = players.isEnd()

    init {
        players.handOutTwoCard(deck)
    }

    fun hit(player: Player) {
        hittablePlayers.find { it == player }
            ?.handOut(deck.draw())
            ?: throw IllegalArgumentException("존재하지 않는 참가자입니다")
    }

    fun result(): BlackJackResult {
        check(isEnd) {
            "게임이 종료되어야 결과를 확인할 수 있습니다"
        }

        return BlackJackResult.of(players.players)
    }
}
