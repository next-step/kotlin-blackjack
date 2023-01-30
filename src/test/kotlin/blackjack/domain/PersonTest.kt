package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PersonTest : StringSpec({

    "참여자는 최초에 2개의 카드를 발급 받는다" {
        val person = Person("손진영", OwnCards(Draw.FIRST_DRAW_COUNT))
        person.name shouldBe "손진영"
        person.ownCards.cards.size shouldBe 2
    }

    "카드를 뽑을 수 있는지 확인한다" {
        val person = Person("손진영", OwnCards(0))
        repeat(2) { person.ownCards.addCard(Card(CardNumber.KING)) }
        person.changeDrawable()
        person.isDrawable shouldBe true
    }

    "카드를 뽑을 수 없는지 확인한다" {
        val person = Person("손진영", OwnCards(0))
        repeat(3) { person.ownCards.addCard(Card(CardNumber.KING)) }
        person.changeDrawable()
        person.isDrawable shouldBe false
    }

    "참여자의 참가비 변경한다" {
        val person = Person("손진영", OwnCards(0))
        person.changeMoney(2.0)
        person.money shouldBe 2.0
    }
})
