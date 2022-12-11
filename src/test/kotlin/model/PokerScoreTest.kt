package model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PokerScoreTest : StringSpec({

    "2~10 까지의 숫자가 넘어오면, 각 숫자의 합을 계산한다" {
        // given
        val cards = Cards(
            mutableListOf(
                Card(PokerNumber.find("2"), PokerShape.CLOVER),
                Card(PokerNumber.find("8"), PokerShape.DIAMOND),
                Card(PokerNumber.find("10"), PokerShape.HEART)
            )
        )

        // when
        val blackJackScore = BlackJackScore(cards)

        // then
        blackJackScore.score shouldBe 20
    }

    "J~K 까지의 숫자가 넘어오면, 각 숫자10 으로 계산하여 합을 한다" {
        // given
        val cards = Cards(
            mutableListOf(
                Card(PokerNumber.find("Jack"), PokerShape.HEART),
                Card(PokerNumber.find("Queen"), PokerShape.HEART),
                Card(PokerNumber.find("King"), PokerShape.HEART)
            )
        )

        // when
        val blackJackScore = BlackJackScore(cards)

        // then
        blackJackScore.score shouldBe 30
    }

    "A 가 있고, 21을 넘지 않으면 11로 계산하여 합을 한다" {
        // given
        val cards = Cards(
            mutableListOf(
                Card(PokerNumber.find("2"), PokerShape.HEART),
                Card(PokerNumber.find("3"), PokerShape.HEART),
                Card(PokerNumber.find("A"), PokerShape.HEART)
            )
        )

        // when
        val blackJackScore = BlackJackScore(cards)

        // then
        blackJackScore.score shouldBe 16
    }

    "A 가 있고, 21을 넘으면 1로 계산하여 합을 한다" {
        // given
        val cards = Cards(
            mutableListOf(
                Card(PokerNumber.find("2"), PokerShape.HEART),
                Card(PokerNumber.find("10"), PokerShape.HEART),
                Card(PokerNumber.find("A"), PokerShape.HEART)
            )
        )

        // when
        val blackJackScore = BlackJackScore(cards)

        // then
        blackJackScore.score shouldBe 13
    }
})
