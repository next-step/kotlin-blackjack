package blackjack.dto

import blackjack.domain.Player

class PlayerResult(players: String) {

    var players: List<Player>
        private set

    init {
        require(players.isNotBlank()) { "입력 값이 없습니다." }
        this.players = players.split(",")
            .map { it.trim() }
            .map { Player(it) }
    }
}
