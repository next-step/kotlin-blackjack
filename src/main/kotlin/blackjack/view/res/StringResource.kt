package blackjack.view.res

import blackjack.model.GameResult
import blackjack.model.Suit

fun getString(suit: Suit) = when (suit) {
    Suit.SPADE -> "스페이드"
    Suit.HEART -> "하트"
    Suit.DIAMOND -> "다이아몬드"
    Suit.CLOVER -> "클로버"
}

fun getString(result: GameResult) = buildString {
    append("${result.gamer.name}: ")
    when(result) {
        is GameResult.Dealer -> append("${result.count.win}승 ${result.count.lose}패 ${result.count.push}무")
        is GameResult.Win -> append("승")
        is GameResult.Lose -> append("패")
        is GameResult.Push -> append("무")
    }
}
