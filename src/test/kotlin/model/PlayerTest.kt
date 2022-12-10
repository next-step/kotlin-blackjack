package model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "플레이어 객체를 생성하면, 이름과 카드를 가진다" {
        // when
        val player = Player("나잘함")

        // then
        player.name shouldBe "나잘함"
        player.cards.shouldNotBeNull()
    }

    "플레이어는 Hit 에 카드를 받으면, 가지고 있는 CARDS 에 추가된다" {
        // given
        val player = Player("나잘함")
        // when
        player.hit(Card(PokerNumber.values().first(), PokerShape.values().first()))

        // then
        player.cards.count() shouldBe 1
    }

    "플레이어의 점수가 딜러보다 높으면, 승리를 반환한다" {
        // given
        val player = createPlayer(listOf(PokerNumber.TEN, PokerNumber.ACE))
        val dealer = createDealer()

        // when
        val winOrLose = player.competeWith(dealer)

        // then
        winOrLose shouldBe WinOrLose.WIN
    }

    "플레이어의 점수가 딜러보다 낮으면, 패배를 반환한다" {
        // given
        val player = createPlayer(listOf(PokerNumber.TWO, PokerNumber.THREE))
        val dealer = createDealer()

        // when
        val winOrLose = player.competeWith(dealer)

        // then
        winOrLose shouldBe WinOrLose.LOSE
    }
})

private fun createPlayer(pokerNumbers: List<PokerNumber>): Player {
    val player = Player("나잘함")
    pokerNumbers.forEach {
        player.hit(Card(it, PokerShape.HEART))
    }
    return player
}

private fun createDealer(): Dealer {
    val dealer = Dealer()
    dealer.hit(Card(PokerNumber.TEN, PokerShape.HEART))
    dealer.hit(Card(PokerNumber.TWO, PokerShape.HEART))
    return dealer
}
