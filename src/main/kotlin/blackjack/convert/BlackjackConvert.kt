package blackjack.convert

import blackjack.domain.card.PlayingCards
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerResults
import blackjack.domain.player.Players
import blackjack.domain.view.model.CardView
import blackjack.domain.view.model.PlayerView
import blackjack.domain.view.model.PlayerViewResult
import blackjack.domain.view.model.PlayerViewResults
import blackjack.domain.view.model.PlayerViews

fun Players.convertView(): PlayerViews = this.map { it.convertView() }
    .run(::PlayerViews)

fun Player.convertView(): PlayerView = PlayerView(
    name = this.getName(),
    cards = this.getCards().cardViews()
)

fun PlayerResults.convertView(): PlayerViewResults = this.map {
    val player = it.player

    PlayerViewResult(
        name = player.getName(),
        cards = player.getCards().cardViews(),
        score = it.score,
    )
}.run(::PlayerViewResults)

private fun PlayingCards.cardViews() = this.map {
    CardView(
        denomination = it.denomination.exposeName,
        suit = it.suit.exposeName,
    )
}
