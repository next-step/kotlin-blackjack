package blackjack.ui.model

import blackjack.domain.player.GamePlayer
import blackjack.domain.player.GamePlayers

object GamePlayerConverter {
    fun convert(player: GamePlayer): GamePlayerOutputModel {
        return GamePlayerOutputModel(player.name, player.cards)
    }

    fun convert(players: GamePlayers): List<GamePlayerOutputModel> {
        return players.players.map(this::convert)
    }
}
