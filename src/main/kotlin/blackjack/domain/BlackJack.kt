package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.player.Player

class BlackJack(
    private val deck: Deck = Deck.default(),
    val players: List<Player>
) {
    val isEnd: Boolean get() = players.all { !it.hittable }

    init {
        require(players.isNotEmpty()) {
            "플레이어는 한 명 이상이어야 합니다."
        }

        players.forEach { player -> player.initHandOut(listOf(deck.draw(), deck.draw())) }
    }

    fun hittablePlayers(): List<Player> {
        return players.filter(Player::hittable)
    }

    fun hit(player: Player) {
        hittablePlayers().find { it == player }
            ?.handOut(deck.draw())
            ?: throw IllegalArgumentException("존재하지 않는 참가자입니다")
    }

    fun result(): BlackJackResult {
        check(isEnd) {
            "게임이 종료되어야 결과를 확인할 수 있습니다"
        }
        return BlackJackResult.of(players)
    }
}
