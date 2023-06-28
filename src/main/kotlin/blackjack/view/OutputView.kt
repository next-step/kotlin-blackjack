package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CardImage
import blackjack.domain.CardLevel
import blackjack.domain.Cards

class OutputView {
    fun viewPlayerCards(playerName: String, cards: Cards) {
        println(playerCardsOutputString(playerName, cards))
    }

    fun viewPlayerScore(playerName: String, cards: Cards, score: Int) {
        println("${playerCardsOutputString(playerName, cards)} - 결과: $score")
    }

    private fun playerCardsOutputString(playerName: String, cards: Cards): String {
        return "${playerName}카드: ${cards.all().joinToString(separator = ", ") { it.displayName() }}"
    }

    private fun Card.displayName() = "${CardLevelView.displayName(cardLevel)}${CardImageView.displayName(cardImage)}"
}

enum class CardImageView(val displayName: String) {
    HEART("하트"), SPADE("스페이드"), CLOVER("클로버"), DIAMOND("다이아");

    companion object {
        fun displayName(cardImage: CardImage) = values().first { it.name == cardImage.name }.displayName
    }
}

enum class CardLevelView(val displayName: String) {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    KING("K"),
    QUEEN("Q");

    companion object {
        fun displayName(cardLevel: CardLevel) = values().first { it.name == cardLevel.name }.displayName
    }
}
