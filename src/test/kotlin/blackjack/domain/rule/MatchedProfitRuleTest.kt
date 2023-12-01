package blackjack.domain.rule

import blackjack.domain.card.Card
import blackjack.domain.card.CardCharacter
import blackjack.domain.card.CardShape
import blackjack.domain.card.DrewCards
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MatchedProfitRuleTest {

    @Test
    fun `참가자가 BUST면, 딜러의 상태와 상관없이 베팅 금액을 잃는다`() {
        val matchedProfitRule = MatchedProfitRule()

        val dealerDrewCards = DrewCards(DefaultScoringRule())
        val participantDrewCards = DrewCards(DefaultScoringRule())

        participantDrewCards.add(Card(CardCharacter.QUEEN, CardShape.SPADE))
        participantDrewCards.add(Card(CardCharacter.KING, CardShape.SPADE))
        participantDrewCards.add(Card(CardCharacter.SEVEN, CardShape.SPADE))

        dealerDrewCards.add(Card(CardCharacter.ACE, CardShape.SPADE))
        dealerDrewCards.add(Card(CardCharacter.KING, CardShape.SPADE))

        val profit = matchedProfitRule.profit(dealerDrewCards, participantDrewCards, 1000)

        profit shouldBe -1000
    }

    @Test
    fun `참가자-BLACKJACK, 딜러-BLACKJACK 이면, 무승부로 베팅 금액은 그대로이다`() {
        val matchedProfitRule = MatchedProfitRule()

        val dealerDrewCards = DrewCards(DefaultScoringRule())
        val participantDrewCards = DrewCards(DefaultScoringRule())

        participantDrewCards.add(Card(CardCharacter.ACE, CardShape.SPADE))
        participantDrewCards.add(Card(CardCharacter.KING, CardShape.SPADE))

        dealerDrewCards.add(Card(CardCharacter.ACE, CardShape.SPADE))
        dealerDrewCards.add(Card(CardCharacter.KING, CardShape.SPADE))

        val profit = matchedProfitRule.profit(dealerDrewCards, participantDrewCards, 1000)

        profit shouldBe 0
    }

    @Test
    fun `참가자만 BLACKJACK이면, 베팅 금액의 1,5배를 받는다`() {
        val matchedProfitRule = MatchedProfitRule()

        val dealerDrewCards = DrewCards(DefaultScoringRule())
        val participantDrewCards = DrewCards(DefaultScoringRule())

        participantDrewCards.add(Card(CardCharacter.ACE, CardShape.SPADE))
        participantDrewCards.add(Card(CardCharacter.KING, CardShape.SPADE))

        dealerDrewCards.add(Card(CardCharacter.ACE, CardShape.SPADE))
        dealerDrewCards.add(Card(CardCharacter.SEVEN, CardShape.SPADE))

        val profit = matchedProfitRule.profit(dealerDrewCards, participantDrewCards, 1000)

        profit shouldBe 1500
    }

    @Test
    fun `딜러만 BLACKJACK이면, 베팅 금액을 잃는다`() {
        val matchedProfitRule = MatchedProfitRule()

        val dealerDrewCards = DrewCards(DefaultScoringRule())
        val participantDrewCards = DrewCards(DefaultScoringRule())

        dealerDrewCards.add(Card(CardCharacter.ACE, CardShape.SPADE))
        dealerDrewCards.add(Card(CardCharacter.KING, CardShape.SPADE))

        participantDrewCards.add(Card(CardCharacter.ACE, CardShape.SPADE))
        participantDrewCards.add(Card(CardCharacter.SEVEN, CardShape.SPADE))
        participantDrewCards.stay()

        val profit = matchedProfitRule.profit(dealerDrewCards, participantDrewCards, 1000)

        profit shouldBe -1000
    }

    @Test
    fun `딜러만 BUST이고, 참가자가 STAY이면 베팅 금액을 받는다`() {
        val matchedProfitRule = MatchedProfitRule()

        val dealerDrewCards = DrewCards(DefaultScoringRule())
        val participantDrewCards = DrewCards(DefaultScoringRule())

        dealerDrewCards.add(Card(CardCharacter.QUEEN, CardShape.SPADE))
        dealerDrewCards.add(Card(CardCharacter.KING, CardShape.SPADE))
        dealerDrewCards.add(Card(CardCharacter.SEVEN, CardShape.SPADE))

        participantDrewCards.add(Card(CardCharacter.TWO, CardShape.SPADE))
        participantDrewCards.add(Card(CardCharacter.KING, CardShape.SPADE))
        participantDrewCards.stay()

        val profit = matchedProfitRule.profit(dealerDrewCards, participantDrewCards, 1000)

        profit shouldBe 1000
    }

    @Test
    fun `딜러가 BUST or BLACKJACK이 아니고 참가자가 STAY면 점수 비교를 한다 - 참가자 승리`() {
        val matchedProfitRule = MatchedProfitRule()

        val dealerDrewCards = DrewCards(DefaultScoringRule())
        val participantDrewCards = DrewCards(DefaultScoringRule())

        dealerDrewCards.add(Card(CardCharacter.QUEEN, CardShape.SPADE))
        dealerDrewCards.add(Card(CardCharacter.SEVEN, CardShape.SPADE))

        participantDrewCards.add(Card(CardCharacter.ACE, CardShape.SPADE))
        participantDrewCards.add(Card(CardCharacter.SEVEN, CardShape.SPADE))
        participantDrewCards.stay()

        val profit = matchedProfitRule.profit(dealerDrewCards, participantDrewCards, 1000)

        profit shouldBe 1000
    }

    @Test
    fun `딜러가 BUST or BLACKJACK이 아니고 참가자가 STAY면 점수 비교를 한다 - 무승부`() {
        val matchedProfitRule = MatchedProfitRule()

        val dealerDrewCards = DrewCards(DefaultScoringRule())
        val participantDrewCards = DrewCards(DefaultScoringRule())

        dealerDrewCards.add(Card(CardCharacter.QUEEN, CardShape.SPADE))
        dealerDrewCards.add(Card(CardCharacter.SEVEN, CardShape.SPADE))

        participantDrewCards.add(Card(CardCharacter.QUEEN, CardShape.HEART))
        participantDrewCards.add(Card(CardCharacter.SEVEN, CardShape.CLUB))
        participantDrewCards.stay()

        val profit = matchedProfitRule.profit(dealerDrewCards, participantDrewCards, 1000)

        profit shouldBe 0
    }

    @Test
    fun `딜러가 BUST or BLACKJACK이 아니고 참가자가 STAY면 점수 비교를 한다 - 참가자 패배`() {
        val matchedProfitRule = MatchedProfitRule()

        val dealerDrewCards = DrewCards(DefaultScoringRule())
        val participantDrewCards = DrewCards(DefaultScoringRule())

        dealerDrewCards.add(Card(CardCharacter.QUEEN, CardShape.SPADE))
        dealerDrewCards.add(Card(CardCharacter.SEVEN, CardShape.SPADE))

        participantDrewCards.add(Card(CardCharacter.TWO, CardShape.SPADE))
        participantDrewCards.add(Card(CardCharacter.SEVEN, CardShape.SPADE))
        participantDrewCards.stay()

        val profit = matchedProfitRule.profit(dealerDrewCards, participantDrewCards, 1000)

        profit shouldBe -1000
    }
}