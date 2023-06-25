package blackjack.ui.model

import blackjack.domain.player.Player
import blackjack.domain.player.Players

object PlayerConverter {
    fun convert(player: Player): PlayerOutputModel {
        return PlayerOutputModel(player.name, player.cards)
    }

    fun convert(players: Players): List<PlayerOutputModel> {
        return players.players.map(this::convert)
    }
}
