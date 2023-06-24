package domain.player

import domain.state.Burst

enum class PlayerGameResult(val description: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무승부"),

    ;

    companion object {
        fun valueOf(player: Player, dealer: Dealer): PlayerGameResult = if (player.state is Burst) LOSE
        else if (dealer.state is Burst) WIN
        else if (player.cards.sum > dealer.cards.sum) WIN
        else if (player.cards.sum == dealer.cards.sum) DRAW
        else LOSE
    }
}
