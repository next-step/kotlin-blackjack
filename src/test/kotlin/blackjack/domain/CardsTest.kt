package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class CardsTest : StringSpec({
    "카드 뭉치에서 랜덤으로 1장의 카드를 뽑는다면 카드 뭉치의 사이즈는 1 줄어든다." {
        // given
        val list = listOf(
            Card(Suite.SPADE, Denomination.ACE),
            Card(Suite.CLOVER, Denomination.EIGHT),
            Card(Suite.HEART, Denomination.JACK)
        )
        val cards = Cards(list)

        // when
        cards.pick()

        // then
        cards.values.size shouldBe list.size - 1
    }

    "ACE 카드가 11점으로 계산되어 21점을 초과한다면 1점으로 계산된다." {
        // given
        val containsAceList = listOf(
            Card(Suite.HEART, Denomination.ACE),
            Card(Suite.HEART, Denomination.FIVE),
            Card(Suite.CLOVER, Denomination.JACK)
        )
        val excludedAceList = listOf(
            Card(Suite.HEART, Denomination.FIVE),
            Card(Suite.CLOVER, Denomination.JACK)
        )
        val cards = Cards(containsAceList)

        // when
        val aceScore = cards.getScore() - excludedAceList.sumOf { it.denomination.score }

        // then
        aceScore shouldBe 1
    }

    "ACE 카드가 포함되었을 때 총 합이 21점 미만이라면 11점으로 계산된다." {
        // given
        val containsAceList = listOf(
            Card(Suite.HEART, Denomination.ACE),
            Card(Suite.HEART, Denomination.TWO),
            Card(Suite.CLOVER, Denomination.FOUR)
        )
        val excludedAceList = listOf(
            Card(Suite.HEART, Denomination.TWO),
            Card(Suite.CLOVER, Denomination.FOUR)
        )
        val cards = Cards(containsAceList)

        // when
        val aceScore = cards.getScore() - excludedAceList.sumOf { it.denomination.score }

        // then
        aceScore shouldBe 11
    }

    "ACE 카드가 포함되었을 때 총 합이 21점이라면 11점으로 계산된다." {
        // given
        val containsAceList = listOf(
            Card(Suite.HEART, Denomination.ACE),
            Card(Suite.HEART, Denomination.JACK)
        )
        val excludedAceList = listOf(
            Card(Suite.HEART, Denomination.JACK),
        )
        val cards = Cards(containsAceList)

        // when
        val aceScore = cards.getScore() - excludedAceList.sumOf { it.denomination.score }

        // then
        aceScore shouldBe 11
    }

    "ACE 카드가 포함되지 않은 카드뭉치의 총 합을 구하면 각 카드의 점수가 더해진 결과가 나온다." {
        // given
        val list = listOf(
            Card(Suite.HEART, Denomination.FIVE),
            Card(Suite.SPADE, Denomination.NINE),
            Card(Suite.CLOVER, Denomination.TWO),
            Card(Suite.CLOVER, Denomination.FOUR)
        )
        val cards = Cards(list)

        // when
        val result = cards.getScore()

        // then
        result shouldBe 20
    }
})
