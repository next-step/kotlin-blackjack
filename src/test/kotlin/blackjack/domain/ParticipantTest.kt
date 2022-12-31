package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class ParticipantTest : StringSpec({
    "player가 뽑은 카드의 점수를 계산할 수 있다." {
        val player = Participant("abc")
        player.addCard(Card(cardSuit = CardSuit.CLOVER, denomination = Denomination.ACE_10))
        player.score shouldBe 10
    }
})
