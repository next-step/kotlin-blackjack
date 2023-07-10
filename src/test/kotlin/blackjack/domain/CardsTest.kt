package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({

    "카드를 추가하면 카드의 리스트의 수가 증가한다." {
        val cards = Cards()
        cards.add(Card(CardType.CLOVER, Denomination.ACE))
        cards.cards.size shouldBe 1
    }

    "카드의 리스트가 있을 때 인덱스가 52가 넘으면 예외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            val cards = Cards()
            cards.get(53)
        }.message shouldBe "더이상 뽑을 카드가 없습니다."
    }

    "에이스 카드가 없다면, 카드들의 점수는 각 끗구의 점수 합계이다." {
        val cards = Cards()
        cards.add(Card(CardType.CLOVER, Denomination.TEN))
        cards.add(Card(CardType.CLOVER, Denomination.KING))
        cards.score() shouldBe 20
    }

    "에이스 카드가 포함되어 있고 스코어의 총합이 21 이하이면 에이스는 11점으로 계산된다." {
        val cards = Cards()
        cards.add(Card(CardType.CLOVER, Denomination.ACE))
        cards.add(Card(CardType.CLOVER, Denomination.KING))
        cards.score() shouldBe 21
    }

    "에이스 카드가 포함되어 있고 스코어의 총합이 21이 넘는다면 에이스는 1점으로 계산된다." {
        val cards = Cards()
        cards.add(Card(CardType.CLOVER, Denomination.ACE))
        cards.add(Card(CardType.HEART, Denomination.TEN))
        cards.add(Card(CardType.CLOVER, Denomination.TWO))
        cards.score() shouldBe 13
    }
})
