package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "chooseHitOrStay()에서 Hit을 선택한 경우, wantHit()가 true를 반환한다" {
        val player = Player("testUser")

        player.chooseHitOrStay(true)

        player.wantHit() shouldBe true
    }

    "chooseHitOrStay()에서 Stay을 선택한 경우, wantHit()가 false를 반환한다" {
        val player = Player("testUser")

        player.chooseHitOrStay(false)

        player.wantHit() shouldBe false
    }

    "chooseHitOrStay()에서 Stay을 선택한 경우, isDone()이 true를 반환한다" {
        val player = Player("testUser")

        player.chooseHitOrStay(false)

        player.isDone() shouldBe true
    }

    "Hit 상태에서 updateStatus()를 호출하는 경우, isDone()이 false를 반환한다" {
        val player = Player("testUser")

        player.chooseHitOrStay(true)
        player.updateStatus()

        player.isDone() shouldBe false
    }

    "Stay 상태에서 updateStatus()를 호출하는 경우, isDone()이 true를 반환한다" {
        val player = Player("testUser")

        player.chooseHitOrStay(false)
        player.updateStatus()

        player.isDone() shouldBe true
    }

    "Player Card의 포인트가 21인 상태에서 updateStatus()를 호출하는 경우, isDone()이 true를 반환한다" {
        val player = Player("testUser")
        player.cards.addCard(Card(CardPattern.Club, CardNumber.TEN))
        player.cards.addCard(Card(CardPattern.Club, CardNumber.JACK))
        player.cards.addCard(Card(CardPattern.Club, CardNumber.A))

        player.updateStatus()

        player.isDone() shouldBe true
    }

    "Player Card의 포인트가 21을 초과한 상태에서 updateStatus()를 호출하는 경우, isDone()이 true를 반환한다" {
        val player = Player("testUser")
        player.cards.addCard(Card(CardPattern.Club, CardNumber.TEN))
        player.cards.addCard(Card(CardPattern.Club, CardNumber.JACK))
        player.cards.addCard(Card(CardPattern.Club, CardNumber.TWO))

        player.updateStatus()

        player.isDone() shouldBe true
    }

    "Player Card의 포인트가 21 미만인 상태에서 updateStatus()를 호출하는 경우, isDone()이 false를 반환한다" {
        val player = Player("testUser")
        player.cards.addCard(Card(CardPattern.Club, CardNumber.TEN))
        player.cards.addCard(Card(CardPattern.Club, CardNumber.TWO))

        player.updateStatus()

        player.isDone() shouldBe false
    }

    "Player가 ACE와 A, TEN을 갖고 있을때 updateStatus()를 호출하는 경우, isDone()이 true를 반환한다" {
        val player = Player("testUser")
        player.cards.addCard(Card(CardPattern.Spade, CardNumber.A))
        player.cards.addCard(Card(CardPattern.Club, CardNumber.A))
        player.cards.addCard(Card(CardPattern.Club, CardNumber.TEN))

        player.updateStatus()

        player.isDone() shouldBe true
    }

    "Player가 ACE와 A, TEN을 갖고 있을때 getPoint()를 호출하는 경우, ACE를 10으로 간주해 21을 반환한다" {
        val player = Player("testUser")
        player.cards.addCard(Card(CardPattern.Spade, CardNumber.A))
        player.cards.addCard(Card(CardPattern.Club, CardNumber.A))
        player.cards.addCard(Card(CardPattern.Club, CardNumber.TEN))

        player.cards.getOptimizedPoint() shouldBe 21
    }

    "Player가 ACE와 JACK, TEN을 갖고 있을때 getPoint()를 호출하는 경우, ACE를 1으로 간주해 21을 반환한다" {
        val player = Player("testUser")
        player.cards.addCard(Card(CardPattern.Spade, CardNumber.A))
        player.cards.addCard(Card(CardPattern.Club, CardNumber.JACK))
        player.cards.addCard(Card(CardPattern.Club, CardNumber.TEN))

        player.cards.getOptimizedPoint() shouldBe 21
    }

})
