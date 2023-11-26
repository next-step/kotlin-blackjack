package blackjack.model.playable.impl

import blackjack.model.BlackJackStatus
import blackjack.model.card.CardFixture
import blackjack.model.pack.impl.ShuffledPack
import blackjack.model.playable.BlackjackScore
import blackjack.model.playable.PlayableResult
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
        player.status() shouldBe BlackJackStatus.ALIVE
        player.isBurst() shouldBe false
        player.score() shouldBe BlackjackScore(12)
    }

    "ACE+KING 이 들어온 경우 ACE 를 11로 인식해, 블랙잭 점수인 21 로 계산 되어야 한다" {
        val player = Player(
            "kong",
            CardFixture.makeCards(CardFixture.ace1, CardFixture.king)
        )
        player.status() shouldBe BlackJackStatus.ALIVE
        player.isBurst() shouldBe false
        player.score() shouldBe BlackjackScore(21)
    }

    "21이 넘는 점수의 카드를 가지고 있는 경우 Burst 상태로써 BlackJackStatus.DIE 상태 이어야한다" {
        val player = Player(
            "gin-tonic",
            CardFixture.makeCards(
                CardFixture.two,
                CardFixture.three,
                CardFixture.four,
                CardFixture.eight,
                CardFixture.nine
            )
        )
        player.status() shouldBe BlackJackStatus.DIE
        player.isBurst() shouldBe true
        player.score() shouldBe BlackjackScore(2 + 3 + 4 + 8 + 9)
    }

    "Burst 상황에서 딜러보다 점수가 낮은경우 LOSE 결과를 반환 해야 한다" {
        val dealer = Dealer(CardFixture.makeCards(CardFixture.queen, CardFixture.nine, CardFixture.three))
        val player = Player(
            "malibu",
            CardFixture.makeCards(CardFixture.ten, CardFixture.jack, CardFixture.ace2)
        )
        val actual = player.result(dealer)
        actual shouldBe PlayableResult.LOSE
    }


    "Burst 가 아닌 상황에서 딜러보다 점수가 낮은경우 LOSE 결과를 반환 해야 한다" {
        val dealer = Dealer(CardFixture.makeCards(CardFixture.queen, CardFixture.ten))
        val player = Player(
            "malibu",
            CardFixture.makeCards(CardFixture.ten, CardFixture.five)
        )
        val actual = player.result(dealer)
        actual shouldBe PlayableResult.LOSE
    }

    "딜러와 점수가 같은 경우 DRAW 결과를 반환 해야 한다" {
        val dealer = Dealer(CardFixture.makeCards(CardFixture.queen, CardFixture.nine, CardFixture.two))
        val player = Player(
            "malibu",
            CardFixture.makeCards(CardFixture.ten, CardFixture.jack, CardFixture.ace2)
        )

        dealer.score() shouldBe BlackjackScore(21)
        player.score() shouldBe BlackjackScore(21)
        player.result(dealer) shouldBe PlayableResult.DRAW
    }
})
