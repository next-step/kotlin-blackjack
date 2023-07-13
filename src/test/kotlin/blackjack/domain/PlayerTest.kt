package blackjack.domain

import blackjack.fixture.PlayerFixture.DEALER_NAME
import blackjack.fixture.PlayerFixture.PLAYER_SONG2_BET_AMOUNT
import blackjack.fixture.PlayerFixture.PLAYER_SONG2_NAME
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeZero
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({

    test("플레이어는 자신의 이름과 카드 리스트, 배팅금액을 가진다.") {
        // given
        val cards = Cards(listOf(Card(Rank.ACE, Suit.HEART), Card(Rank.JACK, Suit.SPADE)))
        val name = PLAYER_SONG2_NAME
        val betAmount = PLAYER_SONG2_BET_AMOUNT

        // when
        val actual = Player(name, cards, betAmount)

        // then
        actual.name shouldBe name
        actual.cards shouldBe cards
        actual.betAmount shouldBe betAmount
    }

    test("현재 가지고 있는 카드의 점수를 반환할 수 있다.") {
        // given
        val cards = Cards(Card(Rank.QUEEN, Suit.HEART))
        val betAmount = PLAYER_SONG2_BET_AMOUNT
        val player = Player(DEALER_NAME, cards, betAmount)
        val expected = 10

        // when, then
        player.score shouldBe expected
    }

    test("카드를 추가로 뽑아 21을 초과할 경우 베팅 금액을 모두 잃게 된다.") {
        // given
        val cards = Cards(Card(Rank.QUEEN, Suit.HEART), Card(Rank.KING, Suit.CLUB))
        val betAmount = PLAYER_SONG2_BET_AMOUNT
        val player = Player(DEALER_NAME, cards, betAmount)

        // when
        player.receiveCard(Card(Rank.NINE, Suit.HEART))

        // then
        player.betAmount shouldBe 0.0
    }
})
