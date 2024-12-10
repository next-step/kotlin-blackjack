package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardSuit
import blackjack.domain.GamePlayers
import blackjack.domain.Player

object ResultView {
    private const val TEXT_DISTRIBUTE_TWO_CARDS = "%s에게 2장의 나누었습니다."
    private const val TEXT_SCORE_RESULT = " - 결과: "
    private const val COMMA = ","
    private const val COLON = ":"
    private const val NAME_HEART = "하트"
    private const val NAME_SPADE = "스페이드"
    private const val NAME_CLOVER = "클로버"
    private const val NAME_DIAMOND = "다이아몬드"
    private const val NAME_CARD = "카드"

    fun printDistributedCardsText(names: List<String>) {
        println()
        println(TEXT_DISTRIBUTE_TWO_CARDS.format(names.joinToString("$COMMA ")))
    }

    fun printPlayersCards(
        gamePlayers: GamePlayers,
        isShownScore: Boolean = false,
    ) {
        if (isShownScore) println()
        gamePlayers.players.forEach { player: Player ->
            printPlayerCards(player, isShownScore)
        }
        println()
    }

    fun printPlayerCards(
        player: Player,
        isShownScore: Boolean = false,
    ) {
        val playerCardResult = "${player.name}${NAME_CARD}$COLON ${player.deck.allCards.joinToString(COMMA){ findCardName(it) }}"
        if (isShownScore) {
            println("${playerCardResult}${TEXT_SCORE_RESULT}${player.findClosestToBlackJackNumber()}")
        } else {
            println(playerCardResult)
        }
    }

    private fun findCardName(card: Card): String = "${findCardNumberName(card.number)}${findCardSuitName(card.suit)}"

    private fun findCardSuitName(cardSuit: CardSuit): String =
        when (cardSuit) {
            CardSuit.SPADE -> NAME_SPADE
            CardSuit.CLOVER -> NAME_CLOVER
            CardSuit.DIAMOND -> NAME_DIAMOND
            CardSuit.HEART -> NAME_HEART
        }

    private fun findCardNumberName(cardNumber: CardNumber): String =
        when (cardNumber) {
            CardNumber.ACE,
            CardNumber.JACK,
            CardNumber.QUEEN,
            CardNumber.KING,
            -> "${cardNumber.name.first()}"

            else -> {
                "${cardNumber.value}"
            }
        }
}
