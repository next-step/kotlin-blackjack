package model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PokerScoreTest : StringSpec({

    "2~10 까지의 숫자가 넘어오면, 각 숫자의 합을 계산한다" {
        // given
        val cards = Cards(
            mutableListOf(
                Card(PokerNumberFinder("2").pokerNumber, PokerShape.CLOVER),
                Card(PokerNumberFinder("8").pokerNumber, PokerShape.DIAMOND),
                Card(PokerNumberFinder("10").pokerNumber, PokerShape.HEART)
            )
        )

        // when
        val pokerScore = PokerScore(cards)

        // then
        pokerScore.score shouldBe 20
    }

    "J~K 까지의 숫자가 넘어오면, 각 숫자10 으로 계산하여 합을 한다" {
        // given
        val cards = Cards(
            mutableListOf(
                Card(PokerNumberFinder("Jack").pokerNumber, PokerShape.HEART),
                Card(PokerNumberFinder("Queen").pokerNumber, PokerShape.HEART),
                Card(PokerNumberFinder("King").pokerNumber, PokerShape.HEART)
            )
        )

        // when
        val pokerScore = PokerScore(cards)

        // then
        pokerScore.score shouldBe 30
    }

    "A 가 있고, 21을 넘지 않으면 11로 계산하여 합을 한다" {
        // given
        val cards = Cards(
            mutableListOf(
                Card(PokerNumberFinder("2").pokerNumber, PokerShape.HEART),
                Card(PokerNumberFinder("3").pokerNumber, PokerShape.HEART),
                Card(PokerNumberFinder("A").pokerNumber, PokerShape.HEART)
            )
        )

        // when
        val pokerScore = PokerScore(cards)

        // then
        pokerScore.score shouldBe 16
    }

    "A 가 있고, 21을 넘으면 1로 계산하여 합을 한다" {
        // given
        val cards = Cards(
            mutableListOf(
                Card(PokerNumberFinder("2").pokerNumber, PokerShape.HEART),
                Card(PokerNumberFinder("10").pokerNumber, PokerShape.HEART),
                Card(PokerNumberFinder("A").pokerNumber, PokerShape.HEART)
            )
        )

        // when
        val pokerScore = PokerScore(cards)

        // then
        pokerScore.score shouldBe 13
    }
})
