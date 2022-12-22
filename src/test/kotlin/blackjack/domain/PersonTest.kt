package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PersonTest : StringSpec({

    "참여자는 최초에 2개의 카드를 발급 받는다" {
        val person = Person("손진영", OwnCards())
        person.name shouldBe "손진영"
        person.ownCards.cards.size shouldBe 2
    }
})
