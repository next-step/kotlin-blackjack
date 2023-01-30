package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GameTest : StringSpec({

    "쉼표로 참여자를 구분한다" {
        val game = Game(listOf(Gamer("a"), Gamer("b"), Gamer("c")))
        game.getParticipantNames() shouldBe listOf("a", "b", "c", "딜러")
    }

    "참여자를 확인한다" {
        val dealer = Dealer()
        val gamer1 = Gamer("a")
        val gamer2 = Gamer("b")
        val gamerList = listOf(gamer1, gamer2)
        val game = Game(gamerList, dealer)
        game.participant shouldBe listOf(gamer1, gamer2, dealer)
    }

    "결과 값을 변경한다" {
        val dealer = Dealer(OwnCards(0))
        dealer.ownCards.addCard(Card(CardNumber.EIGHT))
        val gamer1 = Gamer("a", OwnCards(0))
        gamer1.ownCards.addCard(Card(CardNumber.EIGHT))
        val gamer2 = Gamer("b", OwnCards(0))
        gamer2.ownCards.addCard(Card(CardNumber.EIGHT))
        val gamerList = listOf(gamer1, gamer2)
        val game = Game(gamerList, dealer)
        game.changeResult()
        gamer1.state shouldBe State.DRAW
        gamer2.state shouldBe State.DRAW
    }

    "베팅 금액을 계산한다" {
        val dealer = Dealer(OwnCards(0))
        dealer.ownCards.addCard(Card(CardNumber.EIGHT))
        val gamer1 = Gamer("a", OwnCards(0))
        gamer1.ownCards.addCard(Card(CardNumber.SEVEN))
        gamer1.changeMoney(1000.0)
        val gamer2 = Gamer("b", OwnCards(0))
        gamer2.ownCards.addCard(Card(CardNumber.NINE))
        gamer2.changeMoney(2000.0)
        val gamerList = listOf(gamer1, gamer2)
        val game = Game(gamerList, dealer)
        game.changeResult()
        game.settle()
        game.dealer.money shouldBe -1000.0
    }
})
