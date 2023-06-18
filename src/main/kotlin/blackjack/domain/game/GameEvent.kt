package blackjack.domain.game

private typealias HitOrNot = (String) -> Boolean
private typealias ResultEvent = (String, List<Pair<String, String>>) -> Unit

class GameEvent(
    val hitOrNot: HitOrNot,
    val resultEvent: ResultEvent,
)
