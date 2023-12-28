package game.blackjack.v2.domain

import game.blackjack.v2.domain.card.Card
import game.blackjack.v2.domain.card.CardNumber.ACE
import game.blackjack.v2.domain.card.CardNumber.JACK
import game.blackjack.v2.domain.card.CardNumber.KING
import game.blackjack.v2.domain.card.CardNumber.NINE
import game.blackjack.v2.domain.card.CardNumber.QUEEN
import game.blackjack.v2.domain.card.CardNumber.TEN
import game.blackjack.v2.domain.card.CardNumber.THREE
import game.blackjack.v2.domain.card.CardNumber.TWO
import game.blackjack.v2.domain.card.CardShape.DIAMOND
import game.blackjack.v2.domain.card.CardShape.SPADE
import game.blackjack.v2.domain.participant.Dealer
import game.blackjack.v2.domain.participant.GameResult.LOSE
import game.blackjack.v2.domain.participant.GameResult.WIN
import game.blackjack.v2.domain.participant.Player
import io.kotest.core.spec.style.StringSpec
import org.assertj.core.api.Assertions.assertThat

class FinishGameTest : StringSpec({

    "플레이어 3명 중에 2명이 이기고 1명이 질 경우, 딜러의 게임 결과는 1승 2패이다." {
        val dealer = Dealer().apply { drawCards(listOf(Card(NINE, SPADE), Card(TEN, SPADE))) } // 19 
        val players = listOf(
            Player("Alex").apply { drawCards(listOf(Card(ACE, SPADE), Card(KING, SPADE))) }, // 21
            Player("Jason").apply { drawCards(listOf(Card(TEN, SPADE), Card(KING, SPADE))) }, // 20
            Player("Honggildong").apply { (listOf(Card(TWO, SPADE), Card(THREE, SPADE))) } // 5
        )

        val participants = Participants(dealer = dealer, players = players)
        participants.finishGame()

        assertThat(participants.dealer.gameResultRecord[WIN]).isEqualTo(1)
        assertThat(participants.dealer.gameResultRecord[LOSE]).isEqualTo(2)
    }

    "플레이어 3명 중에 1명이 이기고 2명이 질 경우, 딜러의 게임 결과는 2승 1패이다." {
        val dealer = Dealer().apply { drawCards(listOf(Card(TEN, SPADE), Card(KING, SPADE))) } // 20
        val players = listOf(
            Player("Alex").apply { drawCards(listOf(Card(ACE, SPADE), Card(KING, SPADE))) }, // 21
            Player("Jason").apply { drawCards(listOf(Card(NINE, SPADE), Card(TEN, SPADE))) }, // 19
            Player("Honggildong").apply { (listOf(Card(TWO, SPADE), Card(THREE, SPADE))) } // 5
        )

        val participants = Participants(dealer = dealer, players = players)
        participants.finishGame()

        assertThat(participants.dealer.gameResultRecord[WIN]).isEqualTo(2)
        assertThat(participants.dealer.gameResultRecord[LOSE]).isEqualTo(1)
    }

    "플레이어 3명 중에 딜러가 Bust일 경우, 딜러의 게임 결과는 무조건 0승 3패이다." {
        val dealer = Dealer().apply { drawCards(listOf(Card(TEN, SPADE), Card(JACK, SPADE), Card(KING, SPADE))) } // 30
        val players = listOf(
            Player("Alex").apply { drawCards(listOf(Card(ACE, SPADE), Card(KING, SPADE))) }, // 21
            Player("Jason").apply { drawCards(listOf(Card(NINE, SPADE), Card(TEN, SPADE))) }, // 19
            Player("Honggildong").apply { (listOf(Card(TEN, SPADE), Card(QUEEN, SPADE), Card(KING, DIAMOND))) } // 30
        )

        val participants = Participants(dealer = dealer, players = players)
        participants.finishGame()

        assertThat(participants.dealer.gameResultRecord[WIN]).isEqualTo(0)
        assertThat(participants.dealer.gameResultRecord[LOSE]).isEqualTo(3)
    }

    "플레이어 3명 중에 1명이 Bust이고 1명이 이기고 1명이 질 때, 딜러가 Bust가 아닐 경우, 딜러의 게임 결과는 무조건 2승 1패이다." {
        val dealer = Dealer().apply { drawCards(listOf(Card(TEN, SPADE), Card(KING, SPADE))) } // 20
        val players = listOf(
            Player("Alex").apply { drawCards(listOf(Card(ACE, SPADE), Card(KING, SPADE))) }, // 21
            Player("Jason").apply { drawCards(listOf(Card(NINE, SPADE), Card(TEN, SPADE))) }, // 19
            Player("Honggildong").apply { (listOf(Card(TEN, SPADE), Card(QUEEN, SPADE), Card(KING, DIAMOND))) } // 30
        )

        val participants = Participants(dealer = dealer, players = players)
        participants.finishGame()

        assertThat(participants.dealer.gameResultRecord[WIN]).isEqualTo(2)
        assertThat(participants.dealer.gameResultRecord[LOSE]).isEqualTo(1)
    }
})
