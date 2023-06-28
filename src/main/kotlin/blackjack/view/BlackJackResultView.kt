package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import blackjack.domain.game.CardDistributionResult
import blackjack.domain.game.DealerMatchResult
import blackjack.domain.game.DealerTurnExecuteResult
import blackjack.domain.game.MatchResult
import blackjack.domain.game.MatchResultType
import blackjack.domain.game.PlayerMatchResult
import blackjack.domain.gamer.DealerCard
import blackjack.domain.gamer.PlayerCards
import blackjack.domain.gamer.unWrappings
import blackjack.domain.score.Score

class BlackJackResultView {

    fun display(result: CardDistributionResult) {
        val message = buildString {
            append("\n")
            append(result.makeTitleMessage())
            append(result.makeDealerCardsMessage())
            append(result.makeAllPlayerCardsMessage())
        }
        println(message)
    }

    fun display(playerCards: PlayerCards) {
        println(playerCards.makeDisplayMessage())
    }

    fun display(dealerTurnExecuteResult: DealerTurnExecuteResult) {
        val message = if (dealerTurnExecuteResult.isDistributedOneMoreCard) {
            "딜러는 16이하라 한장의 카드를 더 받았습니다."
        } else {
            "딜러는 17이상이라 카드를 더 받지 않았습니다."
        }
        println()
        println(message)
    }

    fun display(matchResult: MatchResult) {
        println()
        println("딜러 카드: ${matchResult.dealerCards.makeDisplayMessage()} - ${matchResult.dealerCards.score.makeDisplayMessage()}")
        matchResult.allPlayerCards
            .map { "${it.playerName.unWrapping()} 카드: ${it.cards.makeDisplayMessage()} - ${it.cards.score.makeDisplayMessage()}" }
            .forEach { println(it) }
        println()
        println("## 최종 승패")
        println(matchResult.dealerMatchResult.makeDisplayMessage())
        matchResult.playerMatchResults.forEach {
            println(it.makeDisplayMessage())
        }
    }

    private fun CardDistributionResult.makeTitleMessage(): String {
        val names = playerNames.unWrappings().joinToString(", ")
        return "딜러와 ${names}에게 ${distributionCardSize}장씩 나누었습니다."
    }

    private fun CardDistributionResult.makeDealerCardsMessage(): String {
        val openCards = dealerCards.filterIsInstance<DealerCard.Open>()
        val cardsMessage = openCards.joinToString(", ") { openCard -> openCard.card.makeDisplayMessage() }
        return "딜러: $cardsMessage"
    }

    private fun CardDistributionResult.makeAllPlayerCardsMessage(): String {
        return playerCards.joinToString("\n") { playerCard -> playerCard.makeDisplayMessage() }
    }

    private fun PlayerCards.makeDisplayMessage(): String {
        val name = playerName.unWrapping()
        val cardNames = cards.makeDisplayMessage()
        return "${name}카드: $cardNames"
    }

    private fun Score.makeDisplayMessage(): String {
        return "결과: $value"
    }

    private fun Cards.makeDisplayMessage(): String {
        return value.joinToString(", ") { card -> card.makeDisplayMessage() }
    }

    private fun Card.makeDisplayMessage(): String {
        val shapeName = when (shape) {
            CardShape.SPADE -> "스페이드"
            CardShape.HEART -> "하트"
            CardShape.DIAMOND -> "다이아몬드"
            CardShape.CLOVER -> "클로버"
        }
        val denominationName = when (denomination) {
            CardDenomination.ACE -> "A"
            CardDenomination.TWO -> "2"
            CardDenomination.THREE -> "3"
            CardDenomination.FOUR -> "4"
            CardDenomination.FIVE -> "5"
            CardDenomination.SIX -> "6"
            CardDenomination.SEVEN -> "7"
            CardDenomination.EIGHT -> "8"
            CardDenomination.NINE -> "9"
            CardDenomination.TEN -> "10"
            CardDenomination.JACK -> "J"
            CardDenomination.QUEEN -> "Q"
            CardDenomination.KING -> "K"
        }
        return "$denominationName$shapeName"
    }

    private fun DealerMatchResult.makeDisplayMessage(): String {
        return buildString {
            append("딜러:")
            append(" ${winCount}승")
            append(" ${tieCount}무")
            append(" ${loseCount}패")
        }
    }

    private fun PlayerMatchResult.makeDisplayMessage(): String {
        val matchResultText = when (matchResultType) {
            MatchResultType.WIN -> "승"
            MatchResultType.TIE -> "무"
            MatchResultType.LOSE -> "패"
        }
        return buildString {
            append("${playerName.unWrapping()}: ")
            append(matchResultText)
        }
    }
}
