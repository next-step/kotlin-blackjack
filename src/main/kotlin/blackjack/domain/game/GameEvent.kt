package blackjack.domain.game

import blackjack.domain.player.Player

private typealias HitOrNot = (String) -> Boolean
private typealias ResultEvent = (Player) -> Unit

class GameEvent(
    val hitOrNot: HitOrNot,
    val resultEvent: ResultEvent,
)
