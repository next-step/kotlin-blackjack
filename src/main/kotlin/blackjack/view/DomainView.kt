package blackjack.view

import blackjack.domain.card.*
import blackjack.domain.game.BlackJackGameResult
import blackjack.domain.player.Player

fun Card.view(): String {
    val cardNumberName = when (number) {
        CardNumber.ACE -> "A"
        CardNumber.JACK -> "J"
        CardNumber.QUEEN -> "Q"
        CardNumber.KING -> "K"
        else -> "${number.ordinal + 1}"
    }

    val cardKindName = when (kind) {
        CardKind.HEART -> "하트"
        CardKind.DIAMOND -> "다이아몬드"
        CardKind.SPADE -> "스페이드"
        CardKind.CLOVER -> "클로버"
    }

    return cardNumberName + cardKindName
}

fun CardSet.view(): String {
    return cards.joinToString { it.view() }
}

fun Player.view(): String {
    return "${name}카드: ${cardSet.view()}"
}

fun CardScore.view(): String {
    return "결과: $score"
}

fun BlackJackGameResult.view(): String {
    return playerScores.joinToString(separator = "\n") { it.player.view() + " - ${it.cardScore.view()}" }
}

