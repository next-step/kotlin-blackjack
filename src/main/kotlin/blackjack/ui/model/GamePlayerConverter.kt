package blackjack.ui.model

import blackjack.domain.player.GamePlayer
import blackjack.domain.player.GamePlayers

object GamePlayerConverter {
    fun convert(player: GamePlayer): GamePlayerViewModel {
        return GamePlayerViewModel(player.name, player.cards)
    }

    fun convert(players: GamePlayers): List<GamePlayerViewModel> {
        return players.players.map(this::convert)
    }
}
