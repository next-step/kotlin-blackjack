package blackjack.io

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards
import blackjack.domain.card.Suit
import blackjack.domain.result.BlackjackResults
import blackjack.domain.result.DealerResult
import blackjack.domain.result.Result
import blackjack.domain.result.UserResults
import blackjack.domain.user.Dealer
import blackjack.domain.user.Player
import blackjack.domain.user.User
import blackjack.domain.user.Users

object ResultView {
    private const val CARDS_INITIALIZED_FORMAT = "딜러와 %s에게 2장의 카드를 나누었습니다."

    private const val DEALER_CARDS_PRINT_FORMAT = "딜러: %s"
    private const val CARDS_PRINT_FORMAT = "%s카드: %s"

    private const val DEALER_HIT_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."

    private const val DEALER_SCORE_PRINT_FORMAT = "딜러 카드: %s - 결과: %s"
    private const val USER_SCORE_PRINT_FORMAT = "%s카드: %s - 결과: %s"

    private const val RESULT_MESSAGE = "## 최종 승패"
    private const val DEALER_RESULT_PRINT_FORMAT = "딜러: %d승 %d무 %d패"
    private const val USER_RESULT_PRINT_FORMAT = "%s: %s"

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
            stringBuilder.appendLine(printPlayerCards(user))
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
            appendLine(dealerScoreResultFormatting(blackJackResults.dealerResult))
            appendLine(usersScoreResultFormatting(blackJackResults.userResults))
            appendLine(RESULT_MESSAGE)
            appendLine(dealerResultFormatting(blackJackResults.dealerResult))
            append(userResultsFormatting(blackJackResults.userResults))
        }

        print(message)
    }

    private fun dealerScoreResultFormatting(dealerResult: DealerResult): String {
        val dealer = dealerResult.dealer
        return DEALER_SCORE_PRINT_FORMAT.format(cardsToString(dealer.cards), dealer.score)
    }

    private fun usersScoreResultFormatting(userResults: UserResults): StringBuilder {
        val stringBuilder = StringBuilder()
        for (userResult in userResults) {
            stringBuilder.appendLine(userScoreResultFormatting(userResult.user))
        }
        return stringBuilder
    }

    private fun userScoreResultFormatting(user: User): String {
        return USER_SCORE_PRINT_FORMAT.format(user.name, cardsToString(user.cards), user.score)
    }

    private fun dealerResultFormatting(dealerResult: DealerResult): String {
        return DEALER_RESULT_PRINT_FORMAT.format(dealerResult.winCount, dealerResult.drawCount, dealerResult.loseCount)
    }

    private fun userResultsFormatting(userResults: UserResults): StringBuilder {
        val stringBuilder = StringBuilder()
        for (userResult in userResults) {
            val user = userResult.user
            stringBuilder.appendLine(USER_RESULT_PRINT_FORMAT.format(user.name, blackJackResultToString(userResult.result)))
        }
        return stringBuilder
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

    private fun blackJackResultToString(blackjackResult: Result): String {
        return when (blackjackResult) {
            Result.WIN -> "승"
            Result.DRAW -> "무"
            Result.LOSE -> "패"
        }
    }
}
