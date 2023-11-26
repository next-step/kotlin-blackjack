package blackjack.model.playable.impl

import blackjack.model.card.CardFixture
import blackjack.model.pack.impl.ShuffledPack
import blackjack.model.playable.BlackjackScore
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "플레이어는 dealing 시 2장의 카드를 받을 수 있다" {
        shouldNotThrow<IllegalArgumentException> {
            val player = Player("구글")
            player.dealing(ShuffledPack)
            player.cards.count() shouldBe 2
        }
    }

    "플레이어는 hit 시 1장의 카드를 받을 수 있다" {
        shouldNotThrow<IllegalArgumentException> {
            val player = Player("애플")
            player.hit(ShuffledPack)
            player.cards.count() shouldBe 1
        }
    }

    "ACE+ACE+KING 이 들어온 경우 ACE 를 1로 인식해, 스코어가 12 이어야 만 한다" {
        val player = Player(
            "kim",
            CardFixture.makeCards(CardFixture.ace1, CardFixture.ace2, CardFixture.king)
        )
        player.isBurst() shouldBe false
        player.score() shouldBe BlackjackScore(12)
    }

    "ACE+KING 이 들어온 경우 ACE 를 11로 인식해, 블랙잭 점수인 21 로 계산 되어야 한다" {
        val player = Player(
            "kong",
            CardFixture.makeCards(CardFixture.ace1, CardFixture.king)
        )
        player.isBurst() shouldBe false
        player.score() shouldBe BlackjackScore(21)
    }
})
