package blackjack.io

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards
import blackjack.domain.card.Suit
import blackjack.domain.result.BlackjackResults
import blackjack.domain.result.PlayerResult
import blackjack.domain.result.PlayerResults
import blackjack.domain.user.Dealer
import blackjack.domain.user.Player
import blackjack.domain.user.Users

object ResultView {
    private const val CARDS_INITIALIZED_FORMAT = "딜러와 %s에게 2장의 카드를 나누었습니다."

    private const val DEALER_CARDS_PRINT_FORMAT = "딜러: %s"
    private const val CARDS_PRINT_FORMAT = "%s카드: %s"

    private const val DEALER_HIT_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."

    private const val PLAYER_SCORE_PRINT_FORMAT = "%s 카드: %s - 결과: %s"

    private const val RESULT_MESSAGE = "## 최종 수익"
    private const val PLAYER_RESULT_PRINT_FORMAT = "%s: %d"

    fun printCards(dealer: Dealer, users: Users) {
        val message = buildString {
            appendLine()
            appendLine(getCardsInitializedMessage(users))
            appendLine(getDealerCardsString(dealer.cards))
            appendLine(getUsersCardsString(users))
        }
        print(message)
    }

    private fun getCardsInitializedMessage(users: Users): String {
        return CARDS_INITIALIZED_FORMAT.format(users.joinToString(", ") { it.name })
    }

    private fun getDealerCardsString(cards: Cards): String {
        val card = cards.first()
        return DEALER_CARDS_PRINT_FORMAT.format(cardToString(card))
    }

    private fun getUsersCardsString(users: Users): StringBuilder {
        val stringBuilder = StringBuilder()
        for (user in users) {
            stringBuilder.appendLine(getPlayerCardsString(user))
        }
        return stringBuilder
    }

    private fun getPlayerCardsString(player: Player): String {
        return CARDS_PRINT_FORMAT.format(player.name, cardsToString(player.cards))
    }

    fun printPlayerCards(player: Player) {
        println(getPlayerCardsString(player))
    }

    fun printDealerHit() {
        println(DEALER_HIT_MESSAGE)
    }

    fun printResults(blackJackResults: BlackjackResults) {
        val message = buildString {
            appendLine()
            appendLine(playerScoreResultFormatting(blackJackResults.dealerResult.player))
            appendLine(playersScoreResultFormatting(blackJackResults.userResults))
            appendLine(RESULT_MESSAGE)
            appendLine(playerResultFormatting(blackJackResults.dealerResult))
            append(playerResultsFormatting(blackJackResults.userResults))
        }

        print(message)
    }

    private fun playersScoreResultFormatting(playerResults: PlayerResults): StringBuilder {
        val stringBuilder = StringBuilder()
        for (playerResult in playerResults) {
            stringBuilder.appendLine(playerScoreResultFormatting(playerResult.player))
        }
        return stringBuilder
    }

    private fun playerScoreResultFormatting(player: Player): String {
        return PLAYER_SCORE_PRINT_FORMAT.format(player.name, cardsToString(player.cards), player.score)
    }

    private fun playerResultsFormatting(playerResults: PlayerResults): StringBuilder {
        val stringBuilder = StringBuilder()
        for (playerResult in playerResults) {
            stringBuilder.appendLine(playerResultFormatting(playerResult))
        }
        return stringBuilder
    }

    private fun playerResultFormatting(playerResult: PlayerResult): String {
        val player = playerResult.player
        return PLAYER_RESULT_PRINT_FORMAT.format(player.name, playerResult.profit)
    }

    private fun cardNumberToString(cardNumber: CardNumber): String {
        return when (cardNumber) {
            CardNumber.ACE -> "A"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
            else -> cardNumber.score.toString()
        }
    }

    private fun suitToString(suit: Suit): String {
        return when (suit) {
            Suit.SPADE -> "스페이드"
            Suit.DIAMOND -> "다이아몬드"
            Suit.HEART -> "하트"
            Suit.CLOVER -> "클로버"
        }
    }

    private fun cardToString(card: Card): String {
        return cardNumberToString(card.cardNumber) + suitToString(card.suit)
    }

    private fun cardsToString(cards: Cards): String {
        return cards.joinToString(", ", transform = ::cardToString)
    }
}
